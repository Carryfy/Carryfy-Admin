package id.co.admincarryfy.viewmodel

import android.app.Application
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import dagger.hilt.android.scopes.ActivityRetainedScoped
import id.co.admincarryfy.data.database.entities.PerjalananEntities
import id.co.admincarryfy.data.model.Driver
import id.co.admincarryfy.data.model.Home
import id.co.admincarryfy.data.model.ResponseItem
import id.co.admincarryfy.data.model.Value
import id.co.admincarryfy.data.repository.DriverRepository
import id.co.admincarryfy.util.Constant
import id.co.admincarryfy.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ActivityRetainedScoped
class DriverViewModel @ViewModelInject constructor(
    private val driverRepository: DriverRepository,
    application: Application
): AndroidViewModel(application){

    val getPerjalananDatabase: LiveData<List<PerjalananEntities>> = driverRepository.local.getPerjalananDatabase().asLiveData()
    var addDriverRequest: MutableLiveData<Resource<Value>> = MutableLiveData()

    fun addDriverData(driver: Driver) = viewModelScope.launch {
        driverData(driver)
    }

    private suspend fun driverData(driver: Driver) {
        addDriverRequest.value = Resource.Loading()
        if(Constant.isConnectionInternet(getApplication())){
            try{
                val response = driverRepository.remote.addDriverRequest(driver)
                if(response.isSuccessful){
                    response.body()?.let {
                        if(it.value == 1) {
                            val listPerjalanan = driverRepository.local.getPerjalananListDatabase()
                            for (perjalanan in listPerjalanan) {
                                val responsePerjalanan = driverRepository.remote.addPerjalananDriverRequest(noHpUtama = driver.noHpUtama!!, perjalanan = perjalanan.perjalanan)
                                if(responsePerjalanan.isSuccessful){
                                    Log.d("Perjalanan", "driverData: ${responsePerjalanan?.body()?.message}")
                                }else{
                                    Log.d("Perjalanan", "driverData: ${responsePerjalanan?.body()?.message}")
                                }
                            }

                            driverRepository.local.deleteAllPerjalanan()

                            addDriverRequest.value = Resource.Success(it)

                        }else{

                            addDriverRequest.value = Resource.Error("Gagal input driver")
                        }
                    }
                }else{
                    addDriverRequest.value = Resource.Error("${response?.body()?.message}")
                }

            }catch (e: Exception){
                addDriverRequest.value = Resource.Error("${e.message}")
            }
        }else{
            addDriverRequest.value = Resource.Error("Tidak ada koneksi internet")
        }
    }

    fun insertPerjalananDatabase(perjalananEntities: PerjalananEntities) = viewModelScope.launch(Dispatchers.IO){
        driverRepository.local.insertPerjalananDatabase(perjalananEntities)
    }

    fun deletePerjalananDatabase(perjalananEntities: PerjalananEntities) = viewModelScope.launch(Dispatchers.IO){
        driverRepository.local.deletePerjalananDatabase(perjalananEntities)
    }

}