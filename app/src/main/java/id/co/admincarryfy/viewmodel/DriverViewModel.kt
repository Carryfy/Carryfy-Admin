package id.co.admincarryfy.viewmodel

import android.app.Application
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import dagger.hilt.android.scopes.ActivityRetainedScoped
import id.co.admincarryfy.data.database.entities.PerjalananEntities
import id.co.admincarryfy.data.model.*
import id.co.admincarryfy.data.repository.DriverRepository
import id.co.admincarryfy.util.Constant
import id.co.admincarryfy.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

@ActivityRetainedScoped
class DriverViewModel @ViewModelInject constructor(
    private val driverRepository: DriverRepository,
    application: Application
): AndroidViewModel(application){

    val getPerjalananDatabase: LiveData<List<PerjalananEntities>> = driverRepository.local.getPerjalananDatabase().asLiveData()
    var addDriverRequest: MutableLiveData<Resource<Value>> = MutableLiveData()
    val getPerjalananRequest: MutableLiveData<Resource<ResponseList<Perjalanan>>> = MutableLiveData()
    val getDriverByLokasiRequest: MutableLiveData<Resource<ResponseList<Driver>>> = MutableLiveData()
    val updatePesananUserMutable: MutableLiveData<Resource<Value>> = MutableLiveData()
    val editDataDriverMutable: MutableLiveData<Resource<Value>> = MutableLiveData()
    val editRuteDriverMutable: MutableLiveData<Resource<Value>> = MutableLiveData()
    val riwayatDriverMutable: MutableLiveData<Resource<ResponseList<Riwayat>>> = MutableLiveData()
    val updateSaldoMutable: MutableLiveData<Resource<Value>> = MutableLiveData()
    val getLokasiMutable: MutableLiveData<Resource<ResponseList<Lokasi>>> = MutableLiveData()

    fun getLokasi(noHpUtama: String) = viewModelScope.launch {
        getLokasiDriver(noHpUtama)
    }

    private suspend fun getLokasiDriver(noHpUtama: String) {
        getLokasiMutable.value = Resource.Loading()
        if(Constant.isConnectionInternet(getApplication())){
            try{
                val response = driverRepository.remote.getLokasiDriver(noHpUtama)
                getLokasiMutable.value = handleLokasiDriver(response)
            }catch (e: Exception){
                getLokasiMutable.value = Resource.Error("${e.message}")
            }
        }else{
            getLokasiMutable.value = Resource.Error("Tidak ada koneksi internet")
        }
    }

    private fun handleLokasiDriver(response: Response<ResponseList<Lokasi>>): Resource<ResponseList<Lokasi>>? {
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

    fun updateSaldo(idDriver: String, saldo: String) = viewModelScope.launch {
        updateSaldoriver(idDriver, saldo)
    }

    private suspend fun updateSaldoriver(idDriver: String, saldo: String) {
        updateSaldoMutable.value = Resource.Loading()
        if(Constant.isConnectionInternet(getApplication())){
            try{
                val response = driverRepository.remote.updateSaldoDriver(idDriver, saldo)
                updateSaldoMutable.value = handleUpdatePesananUserData(response)
            }catch (e: Exception){
                updateSaldoMutable.value = Resource.Error("${e.message}")
            }
        }else{
            updateSaldoMutable.value = Resource.Error("Tidak ada koneksi internet")
        }
    }

    fun riwayatDriver(noHpUtama: String) = viewModelScope.launch {
        getRiwayatDriver(noHpUtama)
    }

    private suspend fun getRiwayatDriver(noHpUtama: String) {
        riwayatDriverMutable.value = Resource.Loading()
        if(Constant.isConnectionInternet(getApplication())){
            try{
                val response = driverRepository.remote.getRiwayatDriver(noHpUtama)
                riwayatDriverMutable.value = handleGetRiwayatDriver(response)
            }catch (e: Exception){
                riwayatDriverMutable.value = Resource.Error("${e.message}")
            }
        }else{
            riwayatDriverMutable.value = Resource.Error("Tidak ada koneksi internet")
        }
    }

    private fun handleGetRiwayatDriver(response: Response<ResponseList<Riwayat>>): Resource<ResponseList<Riwayat>>? {
        if (response.isSuccessful){
            response.body()?.let {
                val responseBody = response.body()
                return Resource.Success(responseBody)
            }
        }else{
            val responseBody = response.body()
            return Resource.Error(response.errorBody().toString())
        }
        return Resource.Error("${response.errorBody()}")
    }


    fun editRuteDriver(perjalanan: Perjalanan) = viewModelScope.launch {
        editRute(perjalanan)
    }

    private suspend fun editRute(perjalanan: Perjalanan){
        editRuteDriverMutable.value = Resource.Loading()
        if(Constant.isConnectionInternet(getApplication())){
            try{
                val response = driverRepository.remote.editPerjalanan(perjalanan)
                editRuteDriverMutable.value = handleUpdatePesananUserData(response)
            }catch (e: Exception){
                editRuteDriverMutable.value = Resource.Error("${e.message}")
            }
        }else{
            editRuteDriverMutable.value = Resource.Error("Tidak ada koneksi internet")
        }
    }

    fun editDataDriver(driver: Driver) = viewModelScope.launch {
        editDriver(driver)
    }

    private suspend fun editDriver(driver: Driver) {
        editDataDriverMutable.value = Resource.Loading()
        if(Constant.isConnectionInternet(getApplication())){
            try{
                val response = driverRepository.remote.editDataDriver(driver)
                editDataDriverMutable.value = handleUpdatePesananUserData(response)
            }catch (e: Exception){
                editDataDriverMutable.value = Resource.Error("${e.message}")
            }
        }else{
            editDataDriverMutable.value = Resource.Error("Tidak ada koneksi internet")
        }
    }

    fun updatePesananUserData(idPesanan: String, noHpUtama: String) = viewModelScope.launch {
        updatePesanan(idPesanan, noHpUtama)
    }

    private suspend fun updatePesanan(idPesanan: String, noHpUtama: String) {
        updatePesananUserMutable.value = Resource.Loading()
        if(Constant.isConnectionInternet(getApplication())){
            try{
                val response = driverRepository.remote.updatePesananUserRequest(idPesanan, noHpUtama)
                updatePesananUserMutable.value = handleUpdatePesananUserData(response)
            }catch (e: Exception){
                updatePesananUserMutable.value = Resource.Error("${e.message}")
            }
        }else{
            updatePesananUserMutable.value = Resource.Error("Tidak ada koneksi internet")
        }
    }

    private fun handleUpdatePesananUserData(response: Response<Value>): Resource<Value>? {
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

    fun getDriverByLokasiData(lokPenjemputan: String, lokTujuan: String) = viewModelScope.launch {
        driverPerjalananByLokasiData(lokPenjemputan, lokTujuan)
    }

    private suspend fun driverPerjalananByLokasiData(lokPenjemputan: String, lokTujuan: String) {
        getDriverByLokasiRequest.value = Resource.Loading()
        if(Constant.isConnectionInternet(getApplication())){
            try{
                val response = driverRepository.remote.getDriverByLokasi(lokPenjemputan, lokTujuan)
                getDriverByLokasiRequest.value = handleDriverByLokasiData(response)
            }catch (e: Exception){
                getDriverByLokasiRequest.value = Resource.Error("${e.message}")
            }
        }else{
            getDriverByLokasiRequest.value = Resource.Error("Tidak ada koneksi internet")
        }
    }

    private fun handleDriverByLokasiData(response: Response<ResponseList<Driver>>): Resource<ResponseList<Driver>>? {
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

    fun getPerjalananData(noHpUtama: String) = viewModelScope.launch {
        perjalananData(noHpUtama)
    }

    private suspend fun perjalananData(noHpUtama: String) {
        getPerjalananRequest.value = Resource.Loading()
        if(Constant.isConnectionInternet(getApplication())){
            try{
                val response = driverRepository.remote.getPerjalananRequest(noHpUtama)
                getPerjalananRequest.value = handlePerjalananData(response)
            }catch (e: Exception){
                getPerjalananRequest.value = Resource.Error("${e.message}")
            }
        }else{
            getPerjalananRequest.value = Resource.Error("Tidak ada koneksi internet")
        }
    }

    private fun handlePerjalananData(response: Response<ResponseList<Perjalanan>>): Resource<ResponseList<Perjalanan>>? {
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