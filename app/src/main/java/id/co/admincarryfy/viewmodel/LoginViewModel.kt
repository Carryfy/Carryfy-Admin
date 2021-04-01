package id.co.admincarryfy.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.scopes.ActivityRetainedScoped
import id.co.admincarryfy.data.model.Value
import id.co.admincarryfy.data.repository.Repository
import id.co.admincarryfy.util.Constant
import id.co.admincarryfy.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

@ActivityRetainedScoped
class LoginViewModel @ViewModelInject constructor(
    private val repository: Repository,
    application: Application
): AndroidViewModel(application){

    var getLoginAdminResponse: MutableLiveData<Resource<Value>> = MutableLiveData()

    fun getLoginAdmin(username: String, password: String) = viewModelScope.launch {
        loginAdmin(username, password)
    }

    private suspend fun loginAdmin(username: String, password: String) {
        getLoginAdminResponse.value = Resource.Loading()
        if(Constant.isConnectionInternet(getApplication())){
            try{
                val response = repository.remoteDataSource.loginAdminRequest(username, password)
                getLoginAdminResponse.value = handleLoginAdmin(response)
            }catch (e: Exception){
                getLoginAdminResponse.value = Resource.Error("${e.message}")
            }
        }else{
            getLoginAdminResponse.value = Resource.Error("Tidak ada koneksi internet")
        }
    }

    private fun handleLoginAdmin(response: Response<Value>): Resource<Value>? {
        if (response.isSuccessful){
            response.body()?.let {
                val responseBody = response.body()
                return Resource.Success(responseBody)
            }
        }else{
            val responseBody = response.body()
            return Resource.Success(responseBody)
        }
        return Resource.Error("${response.errorBody()}")
    }


}