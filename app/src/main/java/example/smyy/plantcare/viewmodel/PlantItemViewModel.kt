package example.smyy.plantcare.viewmodel

import android.view.View
import androidx.databinding.ObservableField
import example.smyy.plantcare.data.model.db.Plant

class PlantItemViewModel (private val plant: Plant, private val listener: PlantItemViewModelListener?) {

    var name: ObservableField<String>

    var description: ObservableField<String>

    var fertilizerInterval: ObservableField<Int>

    var wateringTime: ObservableField<Int>

    var wateringInterval: ObservableField<Int>

    var fertilizierTime: ObservableField<Int>

    var ImageUrl: ObservableField<String>

    init {
        name = ObservableField(plant.name)
        description = ObservableField(plant.description)
        ImageUrl = ObservableField(plant.ImageUrl)
        fertilizerInterval = ObservableField(plant.fertilizerInterval)
        fertilizierTime = ObservableField(plant.fertilizierTime)
        wateringInterval = ObservableField(plant.wateringInterval)
        wateringTime = ObservableField(plant.wateringTime)
    }

    fun onClickImage(view: View) {
        listener?.onClickImage()
    }
    fun onItemClick(view: View) {
        listener?.onItemClick(plant.plantId)
    }
    interface PlantItemViewModelListener {

        fun onClickImage()
        fun onItemClick(plantId: Int)
    }
}