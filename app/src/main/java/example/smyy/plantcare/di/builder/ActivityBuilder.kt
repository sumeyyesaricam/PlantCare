package example.smyy.plantcare.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import example.smyy.plantcare.ui.addplant.AddPlantActivity
//import example.smyy.plantcare.ui.addplant.AddPlantActivity_MembersInjector
import example.smyy.plantcare.ui.plantlist.PlantListActivity

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindPlantListActivity(): PlantListActivity

    @ContributesAndroidInjector
    abstract fun bindAddPlantActivity(): AddPlantActivity
}