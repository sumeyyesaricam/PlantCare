package example.smyy.plantcare.ui.plant

import android.Manifest
import android.R
import android.app.AlertDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaScannerConnection
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.android.support.AndroidSupportInjection
import example.smyy.plantcare.data.model.db.Plant
import example.smyy.plantcare.databinding.FragmentPlantDetailBinding
import example.smyy.plantcare.ui.DetailCallback
import example.smyy.plantcare.util.Config
import example.smyy.plantcare.util.Config.Companion.MY_CAMERA_PERMISSION_CODE
import example.smyy.plantcare.util.ViewModelFactory
import example.smyy.plantcare.viewmodel.PlantItemViewModel
import example.smyy.plantcare.viewmodel.PlantViewModel
import kotlinx.android.synthetic.main.fragment_plant_detail.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class PlantDetailFragment : Fragment() {


    private lateinit var plantViewModel: PlantViewModel
    lateinit var mDatabase: DatabaseReference
    lateinit var mPlantReference: DatabaseReference

    private var path: String = ""


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
        var binding = FragmentPlantDetailBinding.inflate(inflater, container, false)
        subscribeUi()
        val imgFile = File(plant.ImageUrl)
        if (imgFile.exists()) {
            path=plant.ImageUrl
            val myBitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
            binding.ImgPlant.setImageBitmap(myBitmap)
        }
        binding.spinnerWater.adapter = ArrayAdapter(binding.root.context, R.layout.simple_spinner_item, (1..10).toList())
        binding.spinnerSun.adapter = ArrayAdapter(binding.root.context, R.layout.simple_spinner_item, (1..10).toList())
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
                takePhotoFromCamera()
            }

            override fun onClickRemove(view: View) {
                plantViewModel.removePlant(plant)
                showFragment()
            }

            override fun onClickEdit(view: View) {
                val name = binding.etName.text.toString()
                val description = binding.etDescription.text.toString()
                val waterInterval = binding.spinnerWater.selectedItem
                val sunInterval = binding.spinnerSun.selectedItem
                val waterTime = binding.btnWaterAlarm.text.toString()
                val sunTime = binding.btnSunAlarm.text.toString()
                val item = Plant(name, description, waterInterval as Int, sunInterval as Int, waterTime, sunTime, path)
               // mPlantReference.setValue(item)
                val newItem = mDatabase.child("plant").push()
                newItem.setValue(item)

                item.plantId = plant.plantId
                plantViewModel.updatePlant(item)
                showFragment()

            }
        }
        return binding.root
    }


    private fun takePhotoFromCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, Config.REQUEST_CAMERA)
    }

    fun showFragment() {
        val activity = activity as PlantListActivity
        val fragment = PlantListFragment()
        activity.showFragment(fragment, Config.PlantListFragment_TAG)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            plant = arguments!!.getParcelable(ARG_PARAM_PLANT)
        }
        mDatabase = FirebaseDatabase.getInstance().reference
        mPlantReference = FirebaseDatabase.getInstance().getReference("plant")

    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Config.REQUEST_CAMERA) {
            val thumbnail = data!!.extras!!.get("data") as Bitmap
            ImgPlant.setImageBitmap(thumbnail)
            path = saveImage(thumbnail)
        }
    }

    fun saveImage(myBitmap: Bitmap): String {
        val bytes = ByteArrayOutputStream()
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes)
        val wallpaperDirectory = File((Environment.getExternalStorageDirectory()).toString() + "/plantcare")
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs()
        }

        try {
            val f = File(wallpaperDirectory, ((Calendar.getInstance()
                    .getTimeInMillis()).toString() + ".jpg"))
            f.createNewFile()
            val fo = FileOutputStream(f)
            fo.write(bytes.toByteArray())
            MediaScannerConnection.scanFile(context,
                    arrayOf(f.getPath()),
                    arrayOf("image/jpeg"), null)
            fo.close()
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath())

            return f.getAbsolutePath()
        } catch (e1: Exception) {
            e1.printStackTrace()
        }

        return ""
    }

    private fun subscribeUi() {
        plantViewModel = ViewModelProviders.of(this, viewModelFactory).get(PlantViewModel::class.java)

    }


}

