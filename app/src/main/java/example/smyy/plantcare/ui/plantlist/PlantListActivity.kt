package example.smyy.plantcare.ui.plantlist

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import example.smyy.plantcare.R
import example.smyy.plantcare.data.AppDatabase
import example.smyy.plantcare.data.model.db.Plant
import example.smyy.plantcare.ui.addplant.AddPlantActivity
import kotlinx.android.synthetic.main.activity_plant_list.*
import java.sql.Time
import javax.inject.Inject
import javax.inject.Named
import android.os.AsyncTask
import android.util.Log
import example.smyy.plantcare.data.repository.PlantRepository
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class PlantListActivity : AppCompatActivity() {
    //@Inject
    //lateinit var mPlantListViewModel: PlantListViewModel


    private val disposable = CompositeDisposable()
    lateinit var planViewModel: PlantListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_plant_list)
        planViewModel = ViewModelProviders.of(this).get(PlantListViewModel::class.java)
        addPlant()
        rvPlants.layoutManager = LinearLayoutManager(this)
        val adapter = PlantAdapter(this)
        rvPlants.adapter = adapter
        planViewModel.getPlants().observe(this, Observer { plantList ->
            plantList?.let { adapter.setPlants(it) }
        })

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
        /*var plantDao = AppDatabase.createPersistentDatabase(application).plantDao()

        Single.fromCallable {
            var plant=Plant("104", "lavanta", "ppwwwllww", 4, 3, 5, 5,"")
            plantDao.insertPlant(plant)
        }
                .subscribeOn(Schedulers.io())
                .subscribe(object:SingleObserver<Unit>{
                    override fun onSuccess(t: Unit) {
                        Log.e("success","success")
                    }

                    override fun onSubscribe(d: Disposable) {
                        Log.e("subscribe","subscribe")
                    }

                    override fun onError(e: Throwable) {
                        Log.e("error","error")
                    }
                })*/
                        //{Log.e("error","sg")},{Log.e("success","sg")})

        var plant1=Plant("109", "orkideasdf", "ppwwwllww", 4, 3, 5, 5,"")
        planViewModel.insertPlant(plant1)


        //burası çalışıyor
        /*var plant=Plant("108", "orkide1", "ppwwwllww", 4, 3, 5, 5,"")
        val plantDao = AppDatabase.createPersistentDatabase(application).plantDao()
        val repository = PlantRepository(plantDao)
        disposable.add(repository.insertPlant(plant)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("succes view model", it.toString())
                },{
                    Log.d("Error View model", it.message)
                }))*/

    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }



}
