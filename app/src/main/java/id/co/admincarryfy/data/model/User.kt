package id.co.admincarryfy.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @SerializedName("nama_user")
    @Expose
    var nama_user: String ?= "",

    @SerializedName("jenis_kelamin")
    @Expose
    var jenis_kelamin: String ?= ""
): Parcelable