package id.co.admincarryfy.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Perjalanan (
    @SerializedName("id_detail_rute_driver")
    var idDetailRuteDriver: String ?= "",
    @SerializedName("lok_penjemputan")
    var lokPenjemputan: String ?= "",
    @SerializedName("lok_tujuan")
    var lokTujuan: String ?= "",
    @SerializedName("jam_berangkat")
    var jamBerangkat: String ?= "",
    @SerializedName("jam_sampai")
    var jamSampai: String ?= "",
    @SerializedName("hari_keberangkatan")
    var hariKeberangkatan: String ?= "",
    @SerializedName("biaya")
    var biaya: String ?= ""
): Parcelable