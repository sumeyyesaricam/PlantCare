package example.smyy.plantcare.ui.plantlist

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import example.smyy.plantcare.data.model.db.Plant

class PlantListViewModel internal constructor() : ViewModel() {

    private val plantList = MutableLiveData<List<Plant>>()

    init {

    }

    fun getPlants() = plantList

}