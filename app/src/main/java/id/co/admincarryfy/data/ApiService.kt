package id.co.admincarryfy.data

import id.co.admincarryfy.data.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("login.php")
    suspend fun loginAdminRequest(
        @Field("username") username: String,
        @Field("password") password: String
    ):Response<Value>

    @GET("home_driver.php")
    suspend fun getHomeDriver(): Response<ResponseItem<Home>>

    @GET("tampil_pesanan_user.php")
    suspend fun getPesananUser(
        @Query("tanggal") tanggal: String
    ): Response<ResponseList<Pesanan>>

}