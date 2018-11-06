package example.smyy.plantcare.ui.plant

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.AndroidSupportInjection
import example.smyy.plantcare.R
import example.smyy.plantcare.databinding.FragmentPlantListBinding
import example.smyy.plantcare.util.Config
import example.smyy.plantcare.util.ViewModelFactory
import example.smyy.plantcare.viewmodel.PlantViewModel
import javax.inject.Inject

class PublishedPlantsFragment : Fragment() {


    private lateinit var plantViewModel: PlantViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

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
        plantViewModel.apiGetPlants()
        plantViewModel.getPlantRepos().observe(viewLifecycleOwner, Observer { plantList ->
            val adapter = PlantAdapter()
            binding.rvPlants.adapter = adapter
            plantList.let { adapter.setPlants(it) }
        })
    }

    private fun showAddPlantFragment(){
        val fragment = AddPlantFragment()
        fragmentManager!!.beginTransaction().replace(R.id.fragment_container, fragment, Config.AddPlantFragment_TAG).commit()
    }
    override fun onDestroyView() {
        super.onDestroyView()
    }

}