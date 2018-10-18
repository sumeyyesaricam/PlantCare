package example.smyy.plantcare.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "plants")
data class Plant(@PrimaryKey @ColumnInfo(name = "id") val plantId:String, val name:String,
                 val description:String, val wateringInterval:Int, val fertilizerInterval:Int,
                 val wateringTime:Int, val fertilizierTime:Int,
                 val ImageUrl:String=""){

}
