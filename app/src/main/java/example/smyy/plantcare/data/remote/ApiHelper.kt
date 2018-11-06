package example.smyy.plantcare.data.remote

import example.smyy.plantcare.data.model.db.Plant
import io.reactivex.Single

interface ApiHelper{

     fun doServerGetPlants(): Single<List<Plant>>
}