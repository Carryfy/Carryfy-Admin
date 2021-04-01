package id.co.admincarryfy.data

import id.co.admincarryfy.data.model.Value
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface ApiService {

    @FormUrlEncoded
    @POST("login.php")
    suspend fun loginAdminRequest(
        @Field("username") username: String,
        @Field("password") password: String
    ):Response<Value>

}