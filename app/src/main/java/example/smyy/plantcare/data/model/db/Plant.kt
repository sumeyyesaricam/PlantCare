package example.smyy.plantcare.data.model.db

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "plants")
data class Plant(@PrimaryKey @ColumnInfo(name = "id") val plantId:String, var name:String,
                 var description:String, var wateringInterval:Int, var fertilizerInterval:Int,
                 var wateringTime:Int, var fertilizierTime:Int,
                 val ImageUrl:String=""): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(plantId)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeInt(wateringInterval)
        parcel.writeInt(fertilizerInterval)
        parcel.writeInt(wateringTime)
        parcel.writeInt(fertilizierTime)
        parcel.writeString(ImageUrl)
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
