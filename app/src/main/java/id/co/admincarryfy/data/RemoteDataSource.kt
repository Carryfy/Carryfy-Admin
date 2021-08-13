package id.co.admincarryfy.data

import id.co.admincarryfy.data.model.*
import retrofit2.Response
import java.time.temporal.TemporalQueries
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
){
    suspend fun loginAdminRequest(username: String, password: String): Response<Value>{
        return apiService.loginAdminRequest(username, password)
    }

    suspend fun getHomeRequest(): Response<ResponseItem<Home>>{
        return apiService.getHomeDriver()
    }

    suspend fun getOrderToday(dateStart: String, dateEnd: String): Response<ResponseList<Pesanan>>{
        return apiService.getPesananUser(dateStart, dateEnd)
    }

    suspend fun getDriverRequest(): Response<ResponseList<Driver>>{
        return apiService.getDriverRequest()
    }

    suspend fun addDriverRequest(driver: Driver): Response<Value>{
        return apiService.addDriverRequest(driver)
    }

    suspend fun editPerjalanan(perjalanan: Perjalanan): Response<Value>{
        return apiService.editPerjalananDriver(
            perjalanan.idDetailRuteDriver!!,
            perjalanan.lokPenjemputan!!,
            perjalanan.lokTujuan!!,
            perjalanan.jamBerangkat!!,
            perjalanan.jamSampai!!,
            perjalanan.hariKeberangkatan!!,
            perjalanan.biaya!!
        )
    }

    suspend fun addPerjalananDriverRequest(noHpUtama: String, perjalanan: Perjalanan): Response<Value>{
        return apiService.addPerjalananDriver(
                noHpUtama,
                perjalanan.lokPenjemputan!!,
                perjalanan.lokTujuan!!,
                perjalanan.jamBerangkat!!,
                perjalanan.jamSampai!!,
                perjalanan.hariKeberangkatan!!,
                perjalanan.biaya!!
        )
    }

    suspend fun getPerjalananRequest(noHpUtama: String): Response<ResponseList<Perjalanan>>{
        return apiService.getPerjalanan(noHpUtama)
    }

    suspend fun getDriverByLokasi(lokPenjemputan: String, lokTujuan: String): Response<ResponseList<Driver>>{
        return apiService.getDriverByLokasi(lokPenjemputan, lokTujuan)
    }

    suspend fun updatePesananUserRequest(idPesanan: String, noHpUtama: String): Response<Value>{
        return apiService.updatePesananUser(idPesanan, noHpUtama)
    }

    suspend fun editDataDriver(driver: Driver):  Response<Value>{
        return apiService.editDriverRequest(driver)
    }

    suspend fun getRiwayatDriver(noHpUtama: String): Response<ResponseList<Riwayat>>{
        return apiService.getRiwayatDriver(noHpUtama)
    }

    suspend fun updateSaldoDriver(idDriver: String, saldo:String): Response<Value>{
        return apiService.updateSaldoDriver(idDriver, saldo)
    }

    suspend fun getLokasiDriver(noHpUtama: String): Response<ResponseList<Lokasi>>{
        return apiService.getLokasi(noHpUtama)
    }

}