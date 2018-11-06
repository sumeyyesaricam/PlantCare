package example.smyy.plantcare.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import example.smyy.plantcare.data.model.db.Plant
import example.smyy.plantcare.data.repository.PlantRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PlantViewModel @Inject constructor(private val plantRepository: PlantRepository) : ViewModel() {

    private val disposable = CompositeDisposable()

    private val plantsLiveData: MutableLiveData<List<Plant>> = MutableLiveData<List<Plant>>()
    fun getPlants()= plantRepository.getPlants()

    fun getPlantRepos(): MutableLiveData<List<Plant>> {
        return plantsLiveData
    }

    fun apiGetPlants(){
        disposable.add(plantRepository.apiGetPlants()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({plant->
                    if (plant != null) {
                        plantsLiveData.value = plant
                    }

                },{
                    Log.d("Error insert", it.message)
                }))
    }

    fun insertPlant(plant: Plant) :Unit{
        disposable.add(plantRepository.insertPlant(plant)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("succes insert", it.toString())
                },{
                    Log.d("Error insert", it.message)
                }))
    }

    fun removePlant(plant: Plant):Unit{
        disposable.add(plantRepository.removePlant(plant)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("succes remove", it.toString())
                },{
                    Log.d("Error remove", it.message)
                }))
    }
    fun updatePlant(plant: Plant):Unit{
        disposable.add(plantRepository.updatePlant(plant)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("succes update", it.toString())
                },{
                    Log.d("Error update", it.message)
                }))
    }
    fun getPlant(plantId: Int)= plantRepository.getPlant(plantId)

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}