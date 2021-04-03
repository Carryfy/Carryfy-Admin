package id.co.admincarryfy.data.model

import com.google.gson.annotations.SerializedName

data class ResponseItem<T> (
    @SerializedName("status")
    var status: String ?= "",
    @SerializedName("message")
    var message: String ?= "",
    @SerializedName("data")
    var data: T ?= null
)