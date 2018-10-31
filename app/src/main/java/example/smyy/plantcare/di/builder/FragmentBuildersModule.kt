package example.smyy.plantcare.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import example.smyy.plantcare.ui.plant.AddPlantFragment
import example.smyy.plantcare.ui.plant.PlantDetailFragment
import example.smyy.plantcare.ui.plant.PlantListFragment

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributePlantListFragment(): PlantListFragment

    @ContributesAndroidInjector
    abstract fun contributeAddPlantFragment(): AddPlantFragment

    @ContributesAndroidInjector
    abstract fun contributePlantDetailFragment(): PlantDetailFragment
}