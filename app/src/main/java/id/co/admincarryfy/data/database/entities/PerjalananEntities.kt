package id.co.admincarryfy.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import id.co.admincarryfy.data.model.Perjalanan

@Entity(tableName = "tb_perjalanan")
data class PerjalananEntities (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var perjalanan: Perjalanan
)