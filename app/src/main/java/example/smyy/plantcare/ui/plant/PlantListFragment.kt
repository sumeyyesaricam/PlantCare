package example.smyy.plantcare.ui.plant

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import dagger.android.support.AndroidSupportInjection
import example.smyy.plantcare.databinding.FragmentPlantListBinding
import example.smyy.plantcare.util.ViewModelFactory
import example.smyy.plantcare.viewmodel.PlantViewModel
import javax.inject.Inject

class PlantListFragment : Fragment() {


    private lateinit var plantViewModel: PlantViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentPlantListBinding.inflate(inflater, container, false)
        binding.plantActivity= activity as PlantListActivity?
        binding.rvPlants.layoutManager = GridLayoutManager(binding.root.context, 2)
        subscribeUi(binding)
        return binding.root
    }
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    private fun subscribeUi(binding: FragmentPlantListBinding) {
        plantViewModel = ViewModelProviders.of(this, viewModelFactory).get(PlantViewModel::class.java)
        plantViewModel.getPlants().observe(this, Observer { plantList ->
            val adapter = PlantAdapter()
            binding.rvPlants.adapter = adapter
            plantList?.let { adapter.setPlants(it) }
        })
    }


}