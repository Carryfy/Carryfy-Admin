package id.co.admincarryfy.data

import id.co.admincarryfy.data.database.DatabaseDao
import id.co.admincarryfy.data.database.entities.PerjalananEntities
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val databaseDao: DatabaseDao
) {
    suspend fun insertPerjalananDatabase(perjalananEntities: PerjalananEntities){
        databaseDao.insertPerjalananDatabase(perjalananEntities)
    }

    fun getPerjalananDatabase(): Flow<List<PerjalananEntities>>{
        return databaseDao.getPerjalananDatabase()
    }

    fun getPerjalananListDatabase(): List<PerjalananEntities>{
        return databaseDao.getPerjalananListDatabase()
    }

    suspend fun deletePerjalananDatabase(perjalananEntities: PerjalananEntities){
        databaseDao.deletePerjalananDatabase(perjalananEntities)
    }

    suspend fun deleteAllPerjalanan(){
        databaseDao.deleteAllPerjalanan()
    }

}