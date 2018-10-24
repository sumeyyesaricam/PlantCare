package example.smyy.plantcare.ui.plantlist

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import example.smyy.plantcare.data.AppDatabase
import example.smyy.plantcare.data.repository.PlantRepository
import example.smyy.plantcare.viewmodel.PlantViewModel
import javax.inject.Singleton

@Module
class PlantlistActivityModule{

    @Provides
    internal fun providePlantViewModel(plantRepository: PlantRepository): PlantViewModel {
        return PlantViewModel(plantRepository)
    }

    @Provides
    @Singleton
    internal fun providePlantAdapter(context: Context): PlantAdapter {
        return PlantAdapter(context)
    }

}