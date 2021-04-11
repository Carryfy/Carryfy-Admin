package id.co.admincarryfy.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import id.co.admincarryfy.data.database.entities.PerjalananEntities

@Database(
    entities =[PerjalananEntities::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DatabaseConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun databaseDao(): DatabaseDao
}