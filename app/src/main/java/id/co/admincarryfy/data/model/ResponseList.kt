package id.co.admincarryfy.data.model

import com.google.gson.annotations.SerializedName

data class ResponseList<T> (
    @SerializedName("status")
    var status: String ?= "",
    @SerializedName("message")
    var message: String ?= "",
    @SerializedName("data")
    var data: List<T> ?= null
)