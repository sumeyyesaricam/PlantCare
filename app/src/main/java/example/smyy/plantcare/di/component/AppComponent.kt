package example.smyy.plantcare.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import example.smyy.plantcare.PlantApp
import example.smyy.plantcare.di.builder.ActivityBuilder
import example.smyy.plantcare.di.module.AppModule
import example.smyy.plantcare.di.module.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class, AppModule::class,ActivityBuilder::class, ViewModelModule::class))
interface AppComponent: AndroidInjector<PlantApp> {

    fun inject(application: Application)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun app(application: Application): Builder

        fun build(): AppComponent
    }
}