package example.smyy.plantcare.ui.plant

import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaScannerConnection
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import dagger.android.AndroidInjection
import example.smyy.plantcare.R
import example.smyy.plantcare.data.model.db.Plant
import example.smyy.plantcare.databinding.ActivityNewPlantBinding
import example.smyy.plantcare.ui.DetailCallback
import example.smyy.plantcare.util.Config
import example.smyy.plantcare.util.Config.Companion.ARG_PARAM_PLANT
import example.smyy.plantcare.util.ViewModelFactory
import example.smyy.plantcare.viewmodel.PlantItemViewModel
import example.smyy.plantcare.viewmodel.PlantViewModel
import kotlinx.android.synthetic.main.activity_new_plant.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class NewPlantActivity : AppCompatActivity() {

    private lateinit var plantViewModel: PlantViewModel
    var path: String?=""
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var  binding: ActivityNewPlantBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_plant)
        AndroidInjection.inject(this)
        val bundle=intent.extras
        val arg = NewPlantActivityArgs.fromBundle(bundle)
        val mPlant=arg.plant
        //val mPlant: Plant? = intent.getParcelableExtra<Plant>(ARG_PARAM_PLANT)
        subscribeUi(binding, mPlant)
    }

    private fun subscribeUi(binding: ActivityNewPlantBinding, plant: Plant?) {
        plantViewModel = ViewModelProviders.of(this, viewModelFactory).get(PlantViewModel::class.java)
        binding.spinnerWater.adapter = ArrayAdapter(binding.root.context, android.R.layout.simple_spinner_item, (1..10).toList())
        binding.spinnerSun.adapter = ArrayAdapter(binding.root.context, android.R.layout.simple_spinner_item, (1..10).toList())
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
            TimePickerDialog(this, timeSunSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }
        binding.btnWaterAlarm.setOnClickListener {
            TimePickerDialog(this, timeWaterSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }

        if (plant != null) {
            if(plant.ImageUrl!=null){
                val imgFile = File(plant.ImageUrl)
                if (imgFile.exists()) {
                    path = plant.ImageUrl
                    val myBitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
                    binding.ImgPlant.setImageBitmap(myBitmap)
                }
            }
            binding.viewmodel = PlantItemViewModel(plant)


        } else {
            //binding.btnRemove.isVisible = false
        }
        binding.callback = object : DetailCallback {
            override fun onClickPlantImage(view: View) {
                takePhotoFromCamera()
            }

            override fun onClickRemove(view: View) {
                if (plant != null) {
                    plantViewModel.removePlant(plant)
                    showPlantListActivity()
                }
            }

            override fun onClickEdit(view: View) {
                val name = binding.etName.text.toString()
                val description = binding.etDescription.text.toString()
                val waterInterval = binding.spinnerWater.selectedItem
                val sunInterval = binding.spinnerSun.selectedItem
                val waterTime = binding.btnWaterAlarm.text.toString()
                val sunTime = binding.btnSunAlarm.text.toString()
                val item = Plant(name, description, waterInterval as Int, sunInterval as Int, waterTime, sunTime, path)
                if (plant != null && plant.name!="") {
                    item.plantId = plant.plantId
                    plantViewModel.updatePlant(item)
                } else {
                    plantViewModel.insertPlant(item)
                }
                showPlantListActivity()

            }
        }
    }

    private fun takePhotoFromCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, Config.REQUEST_CAMERA)
    }

    private fun showPlantListActivity() {
        val intent = Intent(this, PlantListActivity::class.java)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Config.REQUEST_CAMERA && data != null) {
            val thumbnail = data.extras.get("data") as Bitmap
            ImgPlant.setImageBitmap(thumbnail)
            path = saveImage(thumbnail)
        }
    }

    private fun saveImage(myBitmap: Bitmap): String {
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
            MediaScannerConnection.scanFile(this,
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

}