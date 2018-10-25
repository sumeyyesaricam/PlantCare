package example.smyy.plantcare.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import example.smyy.plantcare.di.scope.ActivityScope
import example.smyy.plantcare.ui.addplant.AddPlantActivity
//import example.smyy.plantcare.ui.addplant.AddPlantActivity_MembersInjector
import example.smyy.plantcare.ui.plantlist.PlantListActivity
import example.smyy.plantcare.ui.plantlist.PlantlistActivityModule

@Module
abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(PlantlistActivityModule::class))
    abstract fun bindPlantListActivity(): PlantListActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindAddPlantActivity(): AddPlantActivity
}