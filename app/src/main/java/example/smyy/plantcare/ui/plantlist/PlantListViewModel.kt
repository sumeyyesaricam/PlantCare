package example.smyy.plantcare.ui.plantlist

import android.app.Application
import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import example.smyy.plantcare.data.AppDatabase
import example.smyy.plantcare.data.dao.PlantDao
import example.smyy.plantcare.data.model.db.Plant
import example.smyy.plantcare.data.repository.PlantRepository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named

class PlantListViewModel @Inject constructor(private val plantRepository: PlantRepository,application: Application) : ViewModel() {
//class PlantListViewModel(application: Application) : AndroidViewModel(application) {

    private val disposable = CompositeDisposable()


    init {
        //val plantDao = AppDatabase.createPersistentDatabase(application).plantDao()
        //repository = PlantRepository(plantDao)
        //allWords = repository.allWords

    }

    fun getPlants()= plantRepository.getPlants()


    fun insertPlant(plant: Plant) = {
        /*Single.fromCallable {
            repository.insertPlant(plant)
            //plantDao.insertPlant(plant)
        }
                .subscribeOn(Schedulers.io())
                .subscribe(object: SingleObserver<Unit> {
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
        Log.e("ustte","ustte");
        disposable.add(plantRepository.insertPlant(plant)
                .subscribeOn(Schedulers.newThread())
                //.observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("succes view model", it.toString())
                },{
                    Log.d("Error View model", it.message)
                }))

    }

    override fun onCleared() {
        super.onCleared()
        //disposable.clear()
        disposable.dispose()
    }
}