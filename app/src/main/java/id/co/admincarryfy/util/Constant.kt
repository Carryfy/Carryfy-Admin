package id.co.admincarryfy.util

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.text.format.DateFormat
import java.util.*

class Constant {
    companion object{
        const val BASE_URL = "http://carryfy.id/admin_api/"
//        const val BASE_IMAGE_URL = "https://spoonacular.com/cdn/ingredrients_100x100/"

        @SuppressLint("WrongConstant")
        fun isConnectionInternet(context: Context) : Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivityManager != null) {
                val info = connectivityManager.allNetworkInfo
                if (info != null) {
                    for (i in info.indices) {
                        if (info[i].state == NetworkInfo.State.CONNECTED) {
                            return true
                        }
                    }
                }
            }
            return false
        }

        fun getdateToday(): String{
            val cFirst = Calendar.getInstance()

            cFirst.set(Calendar.HOUR_OF_DAY, 0)
            cFirst.set(Calendar.MINUTE, 0)
            cFirst.set(Calendar.SECOND, 0)
            val firstDate = DateFormat.format("yyyy-MM-dd HH:mm:ss", cFirst).toString()

            cFirst.set(Calendar.HOUR_OF_DAY, 23)
            cFirst.set(Calendar.MINUTE, 59)
            cFirst.set(Calendar.SECOND, 59)
            val endDate = DateFormat.format("yyyy-MM-dd HH:mm:ss", cFirst).toString()

            return firstDate+"_"+endDate

        }

    }
}