package id.co.admincarryfy.viewmodel

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.scopes.ActivityRetainedScoped
import id.co.admincarryfy.data.model.*
import id.co.admincarryfy.data.repository.Repository
import id.co.admincarryfy.util.Constant
import id.co.admincarryfy.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
class HomeViewModel @ViewModelInject constructor(
        private val repository: Repository,
        application: Application
): AndroidViewModel(application) {

    var getHomeResponse: MutableLiveData<Resource<ResponseItem<Home>>> = MutableLiveData()
    var getOrderTodayResponse: MutableLiveData<Resource<ResponseList<Pesanan>>> = MutableLiveData()
    var getDriverResponse: MutableLiveData<Resource<ResponseList<Driver>>> = MutableLiveData()

    fun getDriverData() = viewModelScope.launch {
        driverData()
    }

    private suspend fun driverData() {
        getDriverResponse.value = Resource.Loading()
        if(Constant.isConnectionInternet(getApplication())){
            try{
                val response = repository.remoteDataSource.getDriverRequest()
                getDriverResponse.value = handleDriverData(response)
            }catch (e: Exception){
                getDriverResponse.value = Resource.Error("${e.message}")
            }
        }else{
            getDriverResponse.value = Resource.Error("Tidak ada koneksi internet")
        }
    }

    private fun handleDriverData(response: Response<ResponseList<Driver>>): Resource<ResponseList<Driver>>? {
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

    fun getOrderTodayData(dateStart: String, dateEnd: String) = viewModelScope.launch {
        orderTodayData(dateStart, dateEnd)
    }

    private suspend fun orderTodayData(dateStart: String, dateEnd: String) {
        getOrderTodayResponse.value = Resource.Loading()
        if(Constant.isConnectionInternet(getApplication())){
            try{
                val response = repository.remoteDataSource.getOrderToday(dateStart, dateEnd)
                getOrderTodayResponse.value = handleOrderTodayData(response)
            }catch (e: Exception){
                getOrderTodayResponse.value = Resource.Error("${e.message}")
            }
        }else{
            getOrderTodayResponse.value = Resource.Error("Tidak ada koneksi internet")
        }
    }

    private fun handleOrderTodayData(response: Response<ResponseList<Pesanan>>): Resource<ResponseList<Pesanan>>? {
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