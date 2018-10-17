package example.smyy.plantcare.ui.plantlist

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.content.res.Resources
import example.smyy.plantcare.data.model.db.Plant
import example.smyy.plantcare.data.repository.PlantRepository
import javax.inject.Inject
import javax.inject.Named

class PlantListViewModel @Inject constructor(private val plantRepository: PlantRepository) : ViewModel() {

    private val plantList = MutableLiveData<List<Plant>>()

    init {

    }

    fun getPlants() = plantRepository.getPlants()

    fun insertPlant(plant:Plant) = plantRepository.insertPlant(plant)

}