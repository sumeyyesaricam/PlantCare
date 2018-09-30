package example.smyy.plantcare.data.model.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "plants")
data class Plant(@PrimaryKey @ColumnInfo(name = "id") val plantId:String, val name:String,
                 val description:String, val wateringInterval:Int,
                 val imageUrl:String="smyy", val fertilizerInterval:Int,
                 val wateringTime:Int, val fertilizierTime:Int){

}
