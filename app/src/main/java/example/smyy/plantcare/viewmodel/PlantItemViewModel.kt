package example.smyy.plantcare.viewmodel

import android.view.View
import androidx.databinding.ObservableField
import example.smyy.plantcare.data.model.db.Plant
import java.util.*

class PlantItemViewModel(var plant: Plant, private val listener: PlantItemViewModelListener?,
                         val name: ObservableField<String> = ObservableField(plant.name),
                         val description: ObservableField<String> = ObservableField(plant.description),
                         val ImageUrl: ObservableField<String> = ObservableField(plant.ImageUrl),
                         val sunInterval: ObservableField<Int> = ObservableField(plant.sunInterval),
                         val sunTime: ObservableField<String> = ObservableField(plant.sunTime),
                         val wateringInterval: ObservableField<Int> = ObservableField(plant.wateringInterval),
                         val wateringTime: ObservableField<String> = ObservableField(plant.wateringTime),
                         val remainingWaterTime: ObservableField<String> = ObservableField((plant.createdDate.day + plant.wateringInterval - Date().day).toString() + " Gün Sonra"),
                         val remainingSunTime: ObservableField<String> = ObservableField((plant.createdDate.day + plant.sunInterval - Date().day).toString() + " Gün Sonra")) {

    fun onItemClick(view: View) {
        listener?.onItemClick(plant)
    }

    interface PlantItemViewModelListener {

        fun onItemClick(plant: Plant)
    }
}
