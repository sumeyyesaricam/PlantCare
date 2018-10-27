package example.smyy.plantcare.ui.plantlist

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import example.smyy.plantcare.R
import example.smyy.plantcare.ui.addplant.AddPlantActivity
import kotlinx.android.synthetic.main.activity_plant_list.*
import javax.inject.Inject
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import example.smyy.plantcare.BR
import example.smyy.plantcare.PlantApp
import example.smyy.plantcare.databinding.ActivityPlantListBinding
import example.smyy.plantcare.ui.base.BaseActivity
import example.smyy.plantcare.util.ViewModelFactory
import example.smyy.plantcare.viewmodel.PlantViewModel
import kotlin.reflect.KClass

class PlantListActivity : BaseActivity<ActivityPlantListBinding>() {


    lateinit var plantViewModel: PlantViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    lateinit var mAvtivityPlantListBinding: ActivityPlantListBinding


    override fun getLayoutId(): Int {
        return R.layout.activity_plant_list
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAvtivityPlantListBinding = getViewDataBinding()
        mAvtivityPlantListBinding.plantActivity = this
        rvPlants.layoutManager = GridLayoutManager(this,2)
        var plantAdapter=PlantAdapter(this)
        rvPlants.adapter = plantAdapter
        plantViewModel=ViewModelProviders.of(this, viewModelFactory).get(PlantViewModel::class.java)
        plantViewModel.getPlants().observe(this, Observer { plantList ->
            plantList?.let { plantAdapter.setPlants(it) }
        })
    }

    fun onClickAddPlant(view: View) {
        gotoActivity(AddPlantActivity::class, true, null)
        //val intent = Intent(this, AddPlantActivity::class.java)
        //startActivity(intent)
    }

}
