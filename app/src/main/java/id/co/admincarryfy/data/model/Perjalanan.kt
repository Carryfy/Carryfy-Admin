package id.co.admincarryfy.data.model

import com.google.gson.annotations.SerializedName

data class Perjalanan (
    @SerializedName("lok_penjemputan")
    var lokPenjemputan: String ?= "",
    @SerializedName("lok_tujuan")
    var lokTujuan: String ?= "",
    @SerializedName("jam_keberangkatan")
    var jamBerangkat: String ?= "",
    @SerializedName("jam_sampai")
    var jamSampai: String ?= "",
    @SerializedName("hari_keberangkatan")
    var hariKeberangkatan: String ?= "",
    @SerializedName("biaya")
    var biaya: String ?= ""
)