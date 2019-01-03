package example.smyy.plantcare.ui.plant

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import example.smyy.plantcare.data.model.db.Plant
import example.smyy.plantcare.databinding.ItemPlantBinding
import example.smyy.plantcare.util.Config
import example.smyy.plantcare.viewmodel.PlantItemViewModel
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.widget.ImageView
import androidx.navigation.findNavController
import example.smyy.plantcare.R
import example.smyy.plantcare.viewmodel.PlantViewModel
import java.io.File
import java.nio.file.Files.exists


class PlantAdapter(var plants: List<Plant>) : RecyclerView.Adapter<ViewHolder>() {

    var pageType: Int = 0
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(plants[position])
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(ItemPlantBinding.inflate(
                LayoutInflater.from(p0.context), p0, false), pageType)
    }

    override fun getItemCount(): Int {
        return plants.size
    }

    internal fun setPlants(mPlants: List<Plant>, page: Int) {
        this.plants = mPlants
        pageType = page
        notifyDataSetChanged()
    }
}


class ViewHolder(private val binding: ItemPlantBinding, private val pageType: Int) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Plant) {
        binding.apply {
            viewmodel = PlantItemViewModel(item)
            executePendingBindings()
        }
        if (item.ImageUrl != null && item.ImageUrl != "") {
            val imgFile = File(item.ImageUrl)

            if (imgFile.exists()) {
                val myBitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
                binding.ImagePlant.setImageBitmap(myBitmap)
            }
        }
        itemView.setOnClickListener {
            if (pageType == 0) {
                val direction = PlantListFragmentDirections.actionPlantListFragmentToNewPlantActivity(item)
                it.findNavController().navigate(direction)
            } else if (pageType == 1) {
                val direction = PublishedPlantsFragmentDirections.actionPublishedPlantsFragmentToNewPlantActivity(item)
                it.findNavController().navigate(direction)
            }
        }
    }
}
