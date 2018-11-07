package example.smyy.plantcare.data.model.db

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "plants")
data class Plant(var name:String,
                 var description:String, var wateringInterval:Int, var sunInterval:Int,
                 var wateringTime:String, var sunTime:String,
                 val ImageUrl:String="", var createdDate: Date=Date()) : Parcelable {
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeInt(wateringInterval)
        parcel.writeInt(sunInterval)
        parcel.writeString(wateringTime)
        parcel.writeString(sunTime)
        parcel.writeString(ImageUrl)
        parcel.writeInt(plantId)
    }

    @PrimaryKey(autoGenerate = true)
    var plantId:Int=0

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
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
