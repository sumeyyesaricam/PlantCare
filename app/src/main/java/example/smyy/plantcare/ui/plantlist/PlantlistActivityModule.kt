package example.smyy.plantcare.ui.plantlist

import android.app.Application
import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides
import example.smyy.plantcare.data.AppDatabase
import example.smyy.plantcare.data.repository.PlantRepository
import example.smyy.plantcare.di.scope.ActivityScope
import example.smyy.plantcare.viewmodel.PlantViewModel
import javax.inject.Singleton

@Module
class PlantlistActivityModule{

    @ActivityScope
    @Provides
    fun providePlantViewModel(plantRepository: PlantRepository): PlantViewModel {
        return PlantViewModel(plantRepository)
    }

    @ActivityScope
    @Provides
    fun providePlantAdapter(activity: PlantListActivity): PlantAdapter {
        return PlantAdapter(activity)
    }

    @ActivityScope
    @Provides
    fun provideGridLayoutManager(activity: PlantListActivity): GridLayoutManager {
        return GridLayoutManager(activity,2)
    }

}