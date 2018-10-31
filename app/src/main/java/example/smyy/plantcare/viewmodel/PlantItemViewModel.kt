package example.smyy.plantcare.viewmodel

import android.view.View
import androidx.databinding.ObservableField
import example.smyy.plantcare.data.model.db.Plant

class PlantItemViewModel(var plant: Plant, private val listener: PlantItemViewModelListener?,
                         val name :ObservableField<String> = ObservableField(plant.name),
                         val description:ObservableField<String> = ObservableField(plant.description),
                         val ImageUrl:ObservableField<String> = ObservableField(plant.ImageUrl),
                         val fertilizerInterval:ObservableField<Int> = ObservableField(plant.sunInterval),
                         val fertilizierTime :ObservableField<Int> = ObservableField(plant.sunTime),
                         val wateringInterval:ObservableField<Int> = ObservableField(plant.wateringInterval),
                         val wateringTime:ObservableField<Int> = ObservableField(plant.wateringTime)) {

    fun onItemClick(view: View) {
        listener?.onItemClick(plant)
    }

    interface PlantItemViewModelListener {

        fun onItemClick(plant: Plant)
    }
}