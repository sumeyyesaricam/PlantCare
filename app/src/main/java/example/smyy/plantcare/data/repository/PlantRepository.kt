package example.smyy.plantcare.data.repository

import androidx.lifecycle.LiveData
import example.smyy.plantcare.data.dao.PlantDao
import example.smyy.plantcare.data.model.db.Plant
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PlantRepository  @Inject constructor (private val plantDao: PlantDao) {
//class PlantRepository constructor(private val plantDao: PlantDao) {

    val allWords: LiveData<List<Plant>> = plantDao.loadAll()
    fun getPlants() = plantDao.loadAll()

    //fun insertPlant(plant: Plant) : Disposable? {
    fun insertPlant(plant: Plant): Single<Unit> = Single.fromCallable {
        plantDao.insertPlant(plant)
    }

    /*return Observable.fromCallable { plantDao.insertPlant(plant) }
             .subscribeOn(Schedulers.io())
             .observeOn(Schedulers.io())
             .subscribe {

             }*/

}