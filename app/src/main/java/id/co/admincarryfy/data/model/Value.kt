package id.co.admincarryfy.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Value {
    @SerializedName("value")
    @Expose
    var value: Int ?= 0

    @SerializedName("message")
    @Expose
    var message: String ?= ""

}