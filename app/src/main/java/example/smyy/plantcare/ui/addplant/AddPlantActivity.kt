package example.smyy.plantcare.ui.addplant

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import example.smyy.plantcare.R
import android.content.Context
import androidx.lifecycle.Observer
import example.smyy.plantcare.BR
import example.smyy.plantcare.data.model.db.Plant
import example.smyy.plantcare.databinding.ActivityAddPlantBinding
import example.smyy.plantcare.ui.base.BaseActivity
import example.smyy.plantcare.ui.plantlist.PlantListActivity
import example.smyy.plantcare.viewmodel.PlantViewModel
import kotlinx.android.synthetic.main.activity_add_plant.*
import javax.inject.Inject


class AddPlantActivity : BaseActivity<ActivityAddPlantBinding>() {

    @Inject
    lateinit var plantViewModel: PlantViewModel
    lateinit var binding: ActivityAddPlantBinding
    var plant: Plant? = null
    @Inject
    lateinit var context: Context


    override fun getLayoutId(): Int {
        return R.layout.activity_add_plant
    }

    var REQUEST_CAMERA = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewDataBinding()
        binding.plantActivity = this

        val extras = intent.extras ?: return
        var plantId = extras.getInt("EXTRA_PLANT_ID")
        plantViewModel.getPlant(plantId).observe(this, Observer { plant ->
            plant?.let {
                binding.plant = plant
                this.plant = plant
            }
        })
    }

    fun onClickImage(view: View) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_CAMERA)
    }

    fun onClickAddAlarm(view: View) {
        var inflater = layoutInflater.inflate(R.layout.item_alarm, null)
        linearlayout.addView(inflater)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_remove -> {
                plant?.let {
                    plantViewModel.removePlant(plant!!)
                }

                gotoActivity(PlantListActivity::class, true, null)
                return true
            }
            R.id.action_save -> {
                var name = binding.etName.getText().toString()
                var description = binding.etDescription.getText().toString()
                var item= Plant(name, description, 1, 2, 2, 2, "")
                if (plant == null) {
                    plantViewModel.insertPlant(item)
                } else {
                    plantViewModel.updatePlant(item)
                }

                gotoActivity(PlantListActivity::class, true, null)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
