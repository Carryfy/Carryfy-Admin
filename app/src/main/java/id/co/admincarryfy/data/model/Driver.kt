package id.co.admincarryfy.data.model

import com.google.gson.annotations.SerializedName

data class Driver (
    @SerializedName("nama_driver")
    var namaDriver: String ?= "",
    @SerializedName("no_hp_utama")
    var noHpUtama: String ?= "",
    @SerializedName("no_hp_cadangan")
    var noHpCadangan: String ?= "",
    @SerializedName("jenis_kelamin")
    var jenisKelamin: String ?= "",
    @SerializedName("no_ktp_sim")
    var noKtpSim: String ?= "",
    @SerializedName("merk_kendaraan")
    var merkKendaraan: String ?= "",
    @SerializedName("warna_kendaraan")
    var warnaKendaraan: String ?= "",
    @SerializedName("plat_kendaraan")
    var platKendaraan: String ?= "",
    @SerializedName("lokasi_terkini")
    var lokasiTerkini: String ?= "",
    @SerializedName("kursi_kosong")
    var kursiKosong: String ?= "",
    @SerializedName("token_firebase")
    var tokenFirebase: String ?= "",
    @SerializedName("deposit")
    var deposit: String ?= ""
)