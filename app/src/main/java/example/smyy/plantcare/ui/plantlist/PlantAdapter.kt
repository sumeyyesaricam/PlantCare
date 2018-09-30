package example.smyy.plantcare.ui.plantlist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import example.smyy.plantcare.R
import example.smyy.plantcare.data.model.db.Plant
import example.smyy.plantcare.databinding.ItemPlantBinding
import kotlinx.android.synthetic.main.item_plant.view.*

class PlantAdapter(val items : ArrayList<Plant>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val plantid = getItemId(position).toString()
        val plant=items.find { x->x.plantId==plantid }
        holder.apply {
            bind(createOnClickListener(plant!!.plantId), plant)
            itemView.tag = plant
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(ItemPlantBinding.inflate(
            LayoutInflater.from(p0.context), p0, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun createOnClickListener(plantId: String): View.OnClickListener {
        return View.OnClickListener {
            val plantId=plantId
            //val direction = PlantListFragmentDirections.ActionPlantListFragmentToPlantDetailFragment(plantId)
            //it.findNavController().navigate(direction)
        }
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