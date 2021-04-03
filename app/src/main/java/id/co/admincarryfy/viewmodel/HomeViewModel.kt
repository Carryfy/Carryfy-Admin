package id.co.admincarryfy.viewmodel

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.scopes.ActivityRetainedScoped
import id.co.admincarryfy.data.model.Home
import id.co.admincarryfy.data.model.ResponseItem
import id.co.admincarryfy.data.repository.Repository
import id.co.admincarryfy.util.Constant
import id.co.admincarryfy.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

@ActivityRetainedScoped
class HomeViewModel @ViewModelInject constructor(
        private val repository: Repository,
        application: Application
): AndroidViewModel(application) {

    var getHomeResponse: MutableLiveData<Resource<ResponseItem<Home>>> = MutableLiveData()

    fun getHomeData() = viewModelScope.launch {
        homeData()
    }

    private suspend fun homeData() {
        getHomeResponse.value = Resource.Loading()
        if(Constant.isConnectionInternet(getApplication())){
            try{
                val response = repository.remoteDataSource.getHomeRequest()
                getHomeResponse.value = handleHomeData(response)
            }catch (e: Exception){
                getHomeResponse.value = Resource.Error("${e.message}")
            }
        }else{
            getHomeResponse.value = Resource.Error("Tidak ada koneksi internet")
        }
    }

    private fun handleHomeData(response: Response<ResponseItem<Home>>): Resource<ResponseItem<Home>>? {
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