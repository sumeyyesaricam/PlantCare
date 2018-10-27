package example.smyy.plantcare.data.repository

import androidx.lifecycle.LiveData
import example.smyy.plantcare.data.AppDatabase
import example.smyy.plantcare.data.dao.PlantDao
import example.smyy.plantcare.data.model.db.Plant
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PlantRepository  @Inject constructor (private val appDatabase: AppDatabase) {

    fun getPlants() = appDatabase.plantDao().loadAll()

    fun insertPlant(plant: Plant): Single<Long> = Single.fromCallable {
        appDatabase.plantDao().insertPlant(plant)
    }

    fun removePlant(plant: Plant): Single<Unit> = Single.fromCallable {
        appDatabase.plantDao().removePlant(plant)
    }

    fun updatePlant(plant: Plant): Single<Unit> = Single.fromCallable {
        appDatabase.plantDao().updatePlant(plant)
    }

    fun getPlant(plantId: Int)= appDatabase.plantDao().getPlant(plantId)

}