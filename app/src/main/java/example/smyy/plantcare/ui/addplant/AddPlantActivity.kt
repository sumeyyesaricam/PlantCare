package example.smyy.plantcare.ui.addplant

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import example.smyy.plantcare.R
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import example.smyy.plantcare.data.model.db.Plant
import example.smyy.plantcare.databinding.ActivityAddPlantBinding
import example.smyy.plantcare.ui.base.BaseActivity
import example.smyy.plantcare.ui.plantlist.PlantListActivity
import example.smyy.plantcare.util.Config
import example.smyy.plantcare.util.ViewModelFactory
import example.smyy.plantcare.viewmodel.PlantItemViewModel
import example.smyy.plantcare.viewmodel.PlantViewModel
import kotlinx.android.synthetic.main.activity_add_plant.*
import javax.inject.Inject


class AddPlantActivity : BaseActivity<ActivityAddPlantBinding>() ,PlantItemViewModel.PlantItemViewModelListener{
    override fun onItemClick(plantId: Int) {
    }

    override fun onClickImage() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, Config.REQUEST_CAMERA)
    }

    lateinit var plantViewModel: PlantViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var binding: ActivityAddPlantBinding
    var plant: Plant? = null


    override fun getLayoutId(): Int {
        return R.layout.activity_add_plant
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewDataBinding()

        plantViewModel= ViewModelProviders.of(this, viewModelFactory).get(PlantViewModel::class.java)
        val extras = intent.extras ?: return
        var plantId = extras.getInt("EXTRA_PLANT_ID")

        plantViewModel.getPlant(plantId).observe(this, Observer { plant ->
            plant?.let {
                binding.viewmodel = PlantItemViewModel(plant,this)
                this.plant = plant
            }
        })
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
