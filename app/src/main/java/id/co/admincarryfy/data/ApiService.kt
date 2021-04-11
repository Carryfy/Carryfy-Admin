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
        @Query("tanggal_mulai") dateStart: String,
        @Query("tanggal_selesai") dateEnd: String
    ): Response<ResponseList<Pesanan>>

    @GET("tampil_driver.php")
    suspend fun getDriverRequest(): Response<ResponseList<Driver>>

    @POST("tambah_driver.php")
    suspend fun addDriverRequest(
        @Body driver: Driver
    ): Response<Value>

    @FormUrlEncoded
    @POST("tambah_perjalanan_driver.php")
    suspend fun addPerjalananDriver(
        @Field("no_hp_utama") noHpUtama: String,
        @Field("lok_penjemputan") lok_penjemputan: String,
        @Field("lok_tujuan") lok_tujuan: String,
        @Field("jam_berangkat") jam_berangkat: String,
        @Field("jam_sampai") jam_sampai: String,
        @Field("hari_keberangkatan") hari_keberangkatan: String,
        @Field("biaya") biaya: String
    ): Response<Value>

    @GET("tampil_perjalanan.php")
    suspend fun getPerjalanan(
        @Query("no_hp_utama") noHpUtama: String
    ): Response<ResponseList<Perjalanan>>

    @FormUrlEncoded
    @POST("update_saldo_driver.php")
    suspend fun updateSaldoDriver(
        @Field("id_driver_travel") idDriver:String,
        @Field("saldo") saldo: String
    ): Response<Value>

    @GET("tampil_driver_by_lokasi.php")
    suspend fun getDriverByLokasi(
        @Query("lok_penjemputan") lokPenjemputan: String,
        @Query("lok_tujuan") lokTujuan: String
    ): Response<ResponseList<Driver>>

    @FormUrlEncoded
    @POST("update_pesanan_user.php")
    suspend fun updatePesananUser(
        @Field("id_pesanan") idPesanan: String,
        @Field("no_hp_utama") noHpUtama: String
    ): Response<Value>

}