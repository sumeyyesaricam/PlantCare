package example.smyy.plantcare.data.model.db

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "plants")
data class Plant( var name:String,
                 var description:String, var wateringInterval:Int, var fertilizerInterval:Int,
                 var wateringTime:Int, var fertilizierTime:Int,
                 val ImageUrl:String="") {

    @PrimaryKey(autoGenerate = true)
    var plantId:Int=0
}
