package example.smyy.plantcare.ui.plant

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.AndroidSupportInjection
import example.smyy.plantcare.data.model.db.Plant
import example.smyy.plantcare.databinding.FragmentAddPlantBinding
import example.smyy.plantcare.ui.AddCallback
import example.smyy.plantcare.util.Config
import example.smyy.plantcare.util.ViewModelFactory
import example.smyy.plantcare.viewmodel.PlantItemViewModel
import example.smyy.plantcare.viewmodel.PlantViewModel
import javax.inject.Inject


class AddPlantFragment : Fragment() {

    private lateinit var plantViewModel: PlantViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentAddPlantBinding.inflate(inflater, container, false)
        subscribeUi()
        var count: List<Int> = (1..10).toList()
        //binding.spinner_water
        //binding.viewmodel = PlantItemViewModel(plant, null)
        binding.callback = object : AddCallback {

            override fun onClickSave(view: View) {
                val name = binding.etName.text.toString()
                val description = binding.etDescription.getText().toString()
                //val water:Int= binding.spinnerWater.selectedItem as Int
                val item = Plant(name, description, 1, 2, 2, 2, "")
                plantViewModel.insertPlant(item)
                var activity = activity as PlantListActivity
                val fragment = PlantListFragment()
                activity.showFragment(fragment,Config.PlantListFragment_TAG)
            }

            override fun onClickPlantImage(view: View) {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, Config.REQUEST_CAMERA)
            }

        }
        return binding.root
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun subscribeUi() {
        plantViewModel = ViewModelProviders.of(this, viewModelFactory).get(PlantViewModel::class.java)

    }
}