package example.smyy.plantcare.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "plants")
data class Plant(@PrimaryKey @ColumnInfo(name = "id") val plantId:String, var name:String,
                 var description:String, var wateringInterval:Int, var fertilizerInterval:Int,
                 var wateringTime:Int, var fertilizierTime:Int,
                 val ImageUrl:String=""){

}
