package example.smyy.plantcare.ui.plantlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import example.smyy.plantcare.R
import example.smyy.plantcare.data.model.db.Plant
import example.smyy.plantcare.databinding.ItemPlantBinding
import kotlinx.android.synthetic.main.item_plant.view.*

class PlantAdapter(val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var plants = emptyList<Plant>()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = plants[position]
        holder.bind(createOnClickListener(plants[position].plantId),plants[position])
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(ItemPlantBinding.inflate(
            LayoutInflater.from(p0.context), p0, false))
    }

    override fun getItemCount(): Int {
        return plants.size
    }

    private fun createOnClickListener(plantId: String): View.OnClickListener {
        return View.OnClickListener {
            val plantId=plantId
            //val direction = PlantListFragmentDirections.ActionPlantListFragmentToPlantDetailFragment(plantId)
            //it.findNavController().navigate(direction)
        }
    }
    internal fun setPlants(plants: List<Plant>) {
        this.plants = plants
        notifyDataSetChanged()
    }
}

class ViewHolder (private val binding: ItemPlantBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(listener: View.OnClickListener, item: Plant) {
        binding.apply {
            clickListener = listener
            plant = item
            executePendingBindings()
        }
    }
}