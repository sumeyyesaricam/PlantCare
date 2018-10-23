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
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import example.smyy.plantcare.data.AppDatabase
import example.smyy.plantcare.data.dao.PlantDao
import example.smyy.plantcare.data.model.db.Plant
import example.smyy.plantcare.databinding.ActivityAddPlantBinding
import example.smyy.plantcare.ui.plantlist.PlantListViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_add_plant.*
import javax.inject.Inject


class AddPlantActivity : AppCompatActivity() {

    // @Inject
    //lateinit var mPlantListViewModel: PlantListViewModel
    lateinit var binding: ActivityAddPlantBinding
    var REQUEST_CAMERA = 1000
    lateinit var viewModel:PlantListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        //AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_plant)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_plant)
        binding.plantActivity = this

        viewModel = ViewModelProviders.of(this).get(PlantListViewModel::class.java)

        //viewModelFactory = Injection.provideViewModelFactory(this)
        //viewModel = ViewModelProviders.of(this, viewModelFactory).get(PlantListViewModel::class.java)
    }

    fun onClickAddPlant(view: View) {
        var name=binding.etName.getText().toString()
        var description= binding.etName.getText().toString()
        var plant =Plant("2",name,description,1,2,2,2,"")

        viewModel.insertPlant(plant)
        /*Observable.fromCallable({
            viewModel.insertPlant(plant)
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        //viewModelFactory = Injection.provideViewModelFactory(this)
        //viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel::class.java)
        //mPlantListViewModel.insertPlant(plant)*/
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
