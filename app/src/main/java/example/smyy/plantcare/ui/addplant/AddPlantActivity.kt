package example.smyy.plantcare.ui.addplant

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import example.smyy.plantcare.R
import android.content.ClipData.newIntent
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.android.AndroidInjection
import example.smyy.plantcare.data.model.db.Plant
import example.smyy.plantcare.databinding.ActivityAddPlantBinding
import example.smyy.plantcare.ui.plantlist.PlantListViewModel
import kotlinx.android.synthetic.main.activity_add_plant.*
import javax.inject.Inject


class AddPlantActivity : AppCompatActivity() {

    @Inject
    lateinit var mPlantListViewModel: PlantListViewModel
    lateinit var plantview: Plant

    var REQUEST_CAMERA = 1000;
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_plant)
        val binding: ActivityAddPlantBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_plant)
        binding.plantActivity = this
        //plantview= binding.plant!!
    }

    fun onClickAddPlant(view: View) {
        mPlantListViewModel.insertPlant(plantview)
    }

    fun onClickImage(view: View) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_CAMERA)
    }

    fun onClickAddAlarm(view: View) {
        var inflater = layoutInflater.inflate(R.layout.item_alarm, null);
        linearlayout.addView(inflater);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}
