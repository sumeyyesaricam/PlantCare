package example.smyy.plantcare.data.repository

import example.smyy.plantcare.data.dao.PlantDao
import example.smyy.plantcare.data.model.db.Plant
import javax.inject.Inject

class PlantRepository  @Inject constructor (private val plantDao: PlantDao) {

    fun getPlants() = plantDao.loadAll()

    fun insertPlant(plant:Plant) =plantDao.insertPlant(plant)



}