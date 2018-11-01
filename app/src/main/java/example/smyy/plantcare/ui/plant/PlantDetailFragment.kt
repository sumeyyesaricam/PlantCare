package example.smyy.plantcare.ui.plant

import android.R
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.AndroidSupportInjection
import example.smyy.plantcare.data.model.db.Plant
import example.smyy.plantcare.databinding.FragmentPlantDetailBinding
import example.smyy.plantcare.ui.DetailCallback
import example.smyy.plantcare.util.Config
import example.smyy.plantcare.util.ViewModelFactory
import example.smyy.plantcare.viewmodel.PlantItemViewModel
import example.smyy.plantcare.viewmodel.PlantViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class PlantDetailFragment : Fragment() {


    private lateinit var plantViewModel: PlantViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var plant: Plant

    companion object {
        private val ARG_PARAM_PLANT = "PARAM_PLANT"

        fun newInstance(plant: Plant): PlantDetailFragment {
            val fragment = PlantDetailFragment()
            val args = Bundle()
            args.putParcelable(ARG_PARAM_PLANT, plant)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentPlantDetailBinding.inflate(inflater, container, false)
        subscribeUi()
        binding.spinnerWater.adapter= ArrayAdapter(binding.root.context, R.layout.simple_spinner_item, (1..10).toList())
        binding.spinnerSun.adapter= ArrayAdapter(binding.root.context, R.layout.simple_spinner_item, (1..10).toList())
        val cal = Calendar.getInstance()

        val timeWaterSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            binding.btnWaterAlarm.text = SimpleDateFormat("HH:mm").format(cal.time)

        }
        val timeSunSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            binding.btnSunAlarm.text = SimpleDateFormat("HH:mm").format(cal.time)

        }
        binding.btnSunAlarm.setOnClickListener {
            TimePickerDialog(context, timeSunSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }
        binding.btnWaterAlarm.setOnClickListener {
            TimePickerDialog(context, timeWaterSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }
        binding.viewmodel = PlantItemViewModel(plant, null)
        binding.callback = object : DetailCallback {
            override fun onClickPlantImage(view: View) {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, Config.REQUEST_CAMERA)
            }

            override fun onClickRemove(view: View) {
                plantViewModel.removePlant(plant)
                showFragment()
            }

            override fun onClickEdit(view: View) {
                val name = binding.etName.text.toString()
                val description = binding.etDescription.text.toString()
                val waterInterval=binding.spinnerWater.selectedItem
                val sunInterval=binding.spinnerSun.selectedItem
                val waterTime=binding.btnWaterAlarm.text.toString()
                val sunTime=binding.btnSunAlarm.text.toString()
                val item = Plant(name, description, waterInterval as Int, sunInterval as Int, waterTime, sunTime, "")
                item.plantId = plant.plantId
                plantViewModel.updatePlant(item)
                showFragment()

            }
        }
        return binding.root
    }

    fun showFragment() {
        var activity = activity as PlantListActivity
        val fragment = PlantListFragment()
        activity.showFragment(fragment, Config.PlantListFragment_TAG)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            plant = arguments!!.getParcelable(ARG_PARAM_PLANT)
        }
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

