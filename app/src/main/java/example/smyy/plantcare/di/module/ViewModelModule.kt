package example.smyy.plantcare.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import example.smyy.plantcare.di.scope.ViewModelKey
import example.smyy.plantcare.util.ViewModelFactory
import example.smyy.plantcare.viewmodel.PlantViewModel

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(PlantViewModel::class)
    abstract fun bindPlantViewModel(plantViewModel: PlantViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}