package example.smyy.plantcare.data.dao

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.*
import example.smyy.plantcare.data.model.db.Plant

@Dao
interface PlantDao {

    @Query("SELECT * FROM plants")
    fun loadAll(): MutableLiveData<List<Plant>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlant(plant:Plant):Unit

    @Query("SELECT * FROM plants WHERE id = :plantId")
    fun getPlant(plantId:String):LiveData<Plant>

    @Delete
    fun removePlant(plant: Plant):Unit

    @Update
    fun updatePlant(plant: Plant):Unit
}