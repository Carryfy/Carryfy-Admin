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

}