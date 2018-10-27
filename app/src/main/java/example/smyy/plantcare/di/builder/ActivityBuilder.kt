package example.smyy.plantcare.di.builder

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import example.smyy.plantcare.di.scope.ActivityScope
import example.smyy.plantcare.ui.addplant.AddPlantActivity
//import example.smyy.plantcare.ui.addplant.AddPlantActivity_MembersInjector
import example.smyy.plantcare.ui.plantlist.PlantListActivity
import example.smyy.plantcare.ui.plantlist.PlantlistActivityModule

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindPlantListActivity(): PlantListActivity

    @ContributesAndroidInjector
    abstract fun bindAddPlantActivity(): AddPlantActivity
}