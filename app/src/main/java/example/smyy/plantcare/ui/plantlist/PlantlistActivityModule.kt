package example.smyy.plantcare.ui.plantlist

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import example.smyy.plantcare.data.AppDatabase
import example.smyy.plantcare.data.repository.PlantRepository
import example.smyy.plantcare.di.scope.ActivityScope
import example.smyy.plantcare.util.ViewModelFactory
import example.smyy.plantcare.viewmodel.PlantViewModel
import javax.inject.Singleton

//@Module
class PlantlistActivityModule {

    /*@Provides
    internal fun providePlantViewModel(plantRepository: PlantRepository): PlantViewModel {
        return PlantViewModel(plantRepository)
    }*/

    /*@Provides
    internal fun providePlantViewModel(plantViewModel: PlantViewModel): ViewModelProvider.Factory {
        return ViewModelFactory(plantViewModel)
    }

    @Provides
    fun providePlantAdapter(activity: PlantListActivity): PlantAdapter {
        return PlantAdapter(activity)
    }*/

}