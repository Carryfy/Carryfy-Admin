package id.co.admincarryfy.data.model

import com.google.gson.annotations.SerializedName

data class Home (
    @SerializedName("order_user")
    var orderUser: String ?= "",

    @SerializedName("sum_driver")
    var sumDriver: String ?= "",

    @SerializedName("sum_user")
    var sumUser: String ?= ""

)