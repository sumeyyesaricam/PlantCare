package example.smyy.plantcare.data.model.db

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "plants")
data class Plant( var name:String,
                 var description:String, var wateringInterval:Int, var sunInterval:Int,
                 var wateringTime:Int, var sunTime:Int,
                 val ImageUrl:String="") : Parcelable {
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeInt(wateringInterval)
        parcel.writeInt(sunInterval)
        parcel.writeInt(wateringTime)
        parcel.writeInt(sunTime)
        parcel.writeString(ImageUrl)
    }

    @PrimaryKey(autoGenerate = true)
    var plantId:Int=0

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString()) {
        plantId = parcel.readInt()
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Plant> {
        override fun createFromParcel(parcel: Parcel): Plant {
            return Plant(parcel)
        }

        override fun newArray(size: Int): Array<Plant?> {
            return arrayOfNulls(size)
        }
    }
}
