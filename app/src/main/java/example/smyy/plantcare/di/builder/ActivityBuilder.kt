package example.smyy.plantcare.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import example.smyy.plantcare.ui.plant.NewPlantActivity
//import example.smyy.plantcare.ui.addplant.AddPlantActivity_MembersInjector
import example.smyy.plantcare.ui.plant.PlantListActivity

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributePlantListActivity(): PlantListActivity

    @ContributesAndroidInjector
    abstract fun contributeNewPlantActivity(): NewPlantActivity


}