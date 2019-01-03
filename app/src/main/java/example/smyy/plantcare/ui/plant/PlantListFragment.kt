package example.smyy.plantcare.ui.plant

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.AndroidSupportInjection
import example.smyy.plantcare.R
import example.smyy.plantcare.data.model.db.Plant
import example.smyy.plantcare.databinding.FragmentPlantListBinding
import example.smyy.plantcare.util.Config
import example.smyy.plantcare.util.ViewModelFactory
import example.smyy.plantcare.viewmodel.PlantViewModel
import javax.inject.Inject

class PlantListFragment : Fragment() {


    private lateinit var plantViewModel: PlantViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var plantAdapter: PlantAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentPlantListBinding.inflate(inflater, container, false)
        binding.fabBar.setOnClickListener { showAddPlantFragment() }
        subscribeUi(binding)
        return binding.root
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    private fun subscribeUi(binding: FragmentPlantListBinding) {
        plantViewModel = ViewModelProviders.of(this, viewModelFactory).get(PlantViewModel::class.java)
        plantViewModel.getPlants().observe(viewLifecycleOwner, Observer { plantList ->
            binding.rvPlants.adapter = plantAdapter
            plantList.let { plantAdapter.setPlants(it,0) }
        })
    }

    private fun showAddPlantFragment() {
        val plant=Plant("","",0,0,"","")
        val direction = PlantListFragmentDirections.actionPlantListFragmentToNewPlantActivity(plant)
        findNavController().navigate(direction)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}