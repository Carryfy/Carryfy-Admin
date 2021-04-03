package id.co.admincarryfy.data

import id.co.admincarryfy.data.model.Home
import id.co.admincarryfy.data.model.ResponseItem
import id.co.admincarryfy.data.model.Value
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

}