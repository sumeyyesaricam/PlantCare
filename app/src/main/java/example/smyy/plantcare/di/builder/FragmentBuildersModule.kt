package example.smyy.plantcare.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import example.smyy.plantcare.ui.plant.PlantListFragment
import example.smyy.plantcare.ui.plant.PublishedPlantsFragment

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributePlantListFragment(): PlantListFragment

    @ContributesAndroidInjector
    abstract fun contributePublishedPlantsFragment(): PublishedPlantsFragment
}