package example.smyy.plantcare.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import example.smyy.plantcare.ui.plant.PlantAdapter

@Module
class PlantListModule {

    @Provides
    internal fun providePlantAdapter(): PlantAdapter = PlantAdapter(ArrayList())
}