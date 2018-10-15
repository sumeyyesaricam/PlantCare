package example.smyy.plantcare.ui.addplant

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import example.smyy.plantcare.R
import android.content.ClipData.newIntent
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import example.smyy.plantcare.databinding.ActivityAddPlantBinding
import kotlinx.android.synthetic.main.activity_add_plant.*


class AddPlantActivity : AppCompatActivity() {

    var REQUEST_CAMERA = 1000;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_plant)
        val binding: ActivityAddPlantBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_plant)
        binding.plantActivity = this

    }

    fun onClickAddPlant(view: View) {

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
