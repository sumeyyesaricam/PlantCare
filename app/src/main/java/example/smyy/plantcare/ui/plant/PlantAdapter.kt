package example.smyy.plantcare.ui.plant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import example.smyy.plantcare.data.model.db.Plant
import example.smyy.plantcare.databinding.ItemPlantBinding
import example.smyy.plantcare.util.Config
import example.smyy.plantcare.viewmodel.PlantItemViewModel

class PlantAdapter : RecyclerView.Adapter<ViewHolder>() {

    private var plants = emptyList<Plant>()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(plants[position])
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(ItemPlantBinding.inflate(
                LayoutInflater.from(p0.context), p0, false))
    }

    override fun getItemCount(): Int {
        return plants.size
    }

    internal fun setPlants(plants: List<Plant>) {
        this.plants = plants
        notifyDataSetChanged()
    }
}


class ViewHolder(private val binding: ItemPlantBinding) : PlantItemViewModel.PlantItemViewModelListener, RecyclerView.ViewHolder(binding.root) {
    override fun onClickImage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val listener = this

    override fun onItemClick(plant: Plant) {
        val fragment = PlantDetailFragment.newInstance(plant)
        var activity=itemView.context as PlantListActivity
        activity.showFragment(fragment,Config.PlantDetailFragment_TAG)

    }


    fun bind(item: Plant) {
        binding.apply {
            viewmodel = PlantItemViewModel(item, listener)
            executePendingBindings()
        }
    }
}
