package id.co.admincarryfy.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.co.admincarryfy.data.model.Perjalanan

class DatabaseConverter {
    var gson = Gson()

    @TypeConverter
    fun perjalananToString(perjalanan: Perjalanan): String{
        return gson.toJson(perjalanan)
    }

    @TypeConverter
    fun stringToPerjalanan(data: String): Perjalanan{
        val listType = object : TypeToken<Perjalanan>(){}.type
        return gson.fromJson(data, listType)
    }

}