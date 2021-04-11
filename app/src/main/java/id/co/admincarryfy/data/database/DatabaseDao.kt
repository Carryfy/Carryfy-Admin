package id.co.admincarryfy.data.database

import androidx.room.*
import id.co.admincarryfy.data.database.entities.PerjalananEntities
import id.co.admincarryfy.data.model.Perjalanan
import kotlinx.coroutines.flow.Flow

@Dao
interface DatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerjalananDatabase(perjalananEntities: PerjalananEntities)

    @Query("SELECT * FROM tb_perjalanan")
    fun getPerjalananDatabase(): Flow<List<PerjalananEntities>>

    @Query("SELECT * FROM tb_perjalanan")
    fun getPerjalananListDatabase(): List<PerjalananEntities>

    @Delete
    suspend fun deletePerjalananDatabase(perjalananEntities: PerjalananEntities)

    @Query("DELETE FROM tb_perjalanan")
    suspend fun deleteAllPerjalanan()

}