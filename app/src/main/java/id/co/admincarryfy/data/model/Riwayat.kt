package id.co.admincarryfy.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Riwayat(
    @SerializedName("id_riwayat_driver")
    @Expose
    var id_riwayat_driver: String ?= "",

    @SerializedName("no_hp_utama")
    @Expose
    var no_hp_utama: String ?= "",

    @SerializedName("lok_penjemputan")
    @Expose
    var lok_penjemputan: String ?= "",

    @SerializedName("koordinat_penjemputan")
    @Expose
    var koordinat_penjemputan: String ?= "",

    @SerializedName("lok_tujuan")
    @Expose
    var lok_tujuan: String ?= "",

    @SerializedName("koordinat_tujuan")
    @Expose
    var koordinat_tujuan: String ?= "",

    @SerializedName("tgl_berangkat")
    @Expose
    var tgl_berangkat: String ?= "",

    @SerializedName("tgl_sampai")
    @Expose
    var tgl_sampai: Date,

    @SerializedName("penumpang_dewasa")
    @Expose
    var penumpang_dewasa: String ?= "",

    @SerializedName("barang_bawaan")
    @Expose
    var barang_bawaan: String ?= "",

    @SerializedName("biaya")
    @Expose
    var biaya: String ?= "",

    @SerializedName("no_hp_user")
    @Expose
    var no_hp_user: String ?= "",

    @SerializedName("user")
    @Expose
    var user: User
): Parcelable