package id.co.admincarryfy.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Lokasi(
    @SerializedName("id_koordinat_driver_travel")
    @Expose
    var idKoordinat: String ?= "",

    @SerializedName("no_ho_utama")
    @Expose
    var noHpUtam: String ?= "",

    @SerializedName("titik_koordinat")
    @Expose
    var koordinat: String ?= "",

    @SerializedName("tgl_masuk")
    @Expose
    var tglMasuk: Date
): Parcelable