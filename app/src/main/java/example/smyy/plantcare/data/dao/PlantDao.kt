package example.smyy.plantcare.data.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import example.smyy.plantcare.data.model.db.Plant

@Dao
interface PlantDao {

    @Query("SELECT * FROM plants")
    fun loadAll(): LiveData<List<Plant>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlant(plant:Plant)

    @Query("SELECT * FROM plants WHERE id = :plantId")
    fun getPlant(plantId:String):LiveData<Plant>

    @Delete
    fun removePlant(plant: Plant):Unit

    @Update
    fun updatePlant(plant: Plant):Unit
}