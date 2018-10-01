package example.smyy.plantcare.ui.plantlist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import example.smyy.plantcare.R
import example.smyy.plantcare.data.model.db.Plant
import kotlinx.android.synthetic.main.activity_plant_list.*
import java.sql.Time

class PlantListActivity : AppCompatActivity() {
    val plantList: ArrayList<Plant> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_list)

        addPlant()

        rvPlants.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        rvPlants.adapter = PlantAdapter(plantList, this)

    }

    fun addPlant() {
        plantList.add(Plant("1", "kıvırcık", "asdfghj", 4,
                "", 3, 5, 5))

        plantList.add(Plant("2", "kaktüs", "asdfghj", 4,
                "", 3, 2, 2))

        plantList.add(Plant("3", "gül", "asdfghj", 4,
                "", 3, 2, 2))
    }
}
