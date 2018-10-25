package example.smyy.plantcare.ui.plantlist

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import example.smyy.plantcare.R
import example.smyy.plantcare.data.model.db.Plant
import example.smyy.plantcare.databinding.ItemPlantBinding
import example.smyy.plantcare.ui.addplant.AddPlantActivity
import example.smyy.plantcare.util.Config
import kotlinx.android.synthetic.main.item_plant.view.*
import javax.inject.Inject

class PlantAdapter(val activity: PlantListActivity) : RecyclerView.Adapter<ViewHolder>() {

    private var plants = emptyList<Plant>()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(createOnClickListener(plants[position]),plants[position])
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(ItemPlantBinding.inflate(
            LayoutInflater.from(p0.context), p0, false))
    }

    override fun getItemCount(): Int {
        return plants.size
    }

    private fun createOnClickListener(plant: Plant): View.OnClickListener {
        return View.OnClickListener {
            val intent = Intent(activity.applicationContext, AddPlantActivity::class.java)
            intent.putExtra("EXTRA_PLANT", plant.plantId)
            activity.applicationContext.startActivity(intent)
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