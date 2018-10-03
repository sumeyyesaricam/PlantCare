package example.smyy.plantcare.ui.plantlist

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import example.smyy.plantcare.R
import example.smyy.plantcare.data.model.db.Plant
import example.smyy.plantcare.ui.addplant.AddPlantActivity
import kotlinx.android.synthetic.main.activity_plant_list.*
import java.sql.Time

class PlantListActivity : AppCompatActivity() {
    val plantList: ArrayList<Plant> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_list)

        addPlant()

        rvPlants.layoutManager = LinearLayoutManager(this)
        rvPlants.adapter = PlantAdapter(plantList, this)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_add -> {
                val intent = Intent(this, AddPlantActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    fun addPlant() {
        plantList.add(Plant("1", "kıvırcık", "deneme", 4,
                "", 3, 5, 5))

        plantList.add(Plant("2", "kaktüs", "asdfghj", 4,
                "", 3, 2, 2))

        plantList.add(Plant("3", "gül", "asdfghj", 4,
                "", 3, 2, 2))
    }


}
