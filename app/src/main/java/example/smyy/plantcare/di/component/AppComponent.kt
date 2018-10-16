package example.smyy.plantcare.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import example.smyy.plantcare.PlantApp
import example.smyy.plantcare.di.builder.ActivityBuilder
import example.smyy.plantcare.di.module.AppModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityBuilder::class])
interface AppComponent{

    abstract fun inject(application: PlantApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}