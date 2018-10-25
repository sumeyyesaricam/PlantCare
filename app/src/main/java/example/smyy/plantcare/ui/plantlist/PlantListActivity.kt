package example.smyy.plantcare.ui.plantlist

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
import androidx.recyclerview.widget.GridLayoutManager
import example.smyy.plantcare.BR
import example.smyy.plantcare.PlantApp
import example.smyy.plantcare.databinding.ActivityPlantListBinding
import example.smyy.plantcare.ui.base.BaseActivity
import example.smyy.plantcare.viewmodel.PlantViewModel


class PlantListActivity : BaseActivity<ActivityPlantListBinding>() {

    @Inject
    lateinit var planViewModel: PlantViewModel

    @Inject
    lateinit var mLayoutManager: GridLayoutManager

    @Inject
    lateinit var plantAdapter: PlantAdapter

    lateinit var mAvtivityPlantListBinding: ActivityPlantListBinding


    override fun getLayoutId(): Int {
        return R.layout.activity_plant_list
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAvtivityPlantListBinding = getViewDataBinding()
        mAvtivityPlantListBinding.plantActivity = this
        rvPlants.layoutManager = mLayoutManager
        rvPlants.adapter = plantAdapter
        planViewModel.getPlants().observe(this, Observer { plantList ->
            plantList?.let { plantAdapter.setPlants(it) }
        })
    }

    fun onClickAddPlant(view: View) {
        val intent = Intent(this, AddPlantActivity::class.java)
        startActivity(intent)
    }

}
