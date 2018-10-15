package example.smyy.plantcare.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import example.smyy.plantcare.PlantApp
import javax.inject.Singleton

@Singleton
@Component
interface AppComponent{
    abstract fun inject(application: PlantApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}