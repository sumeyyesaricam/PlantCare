package example.smyy.plantcare.data.remote

import com.rx2androidnetworking.Rx2AndroidNetworking
import example.smyy.plantcare.data.AppDatabase
import example.smyy.plantcare.data.model.db.Plant
import example.smyy.plantcare.util.ApiEndPoint
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppApiHelper : ApiHelper {

    override fun doServerGetPlants(): Single<List<Plant>> {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_GET_PLANTS)
                .build()
                .getObjectListSingle(Plant::class.java)
    }

}