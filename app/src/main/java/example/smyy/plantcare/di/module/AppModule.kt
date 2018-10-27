package example.smyy.plantcare.di.module

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import dagger.Module
import dagger.Provides
import example.smyy.plantcare.data.AppDatabase
import example.smyy.plantcare.data.repository.PlantRepository
import javax.inject.Singleton

@Module
class AppModule {


    @Provides
    @Singleton
    fun provideContext(application: Application) : Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun providesAppDatabase(context: Context): AppDatabase = AppDatabase.createPersistentDatabase(context)


    @Provides
    @Singleton
    fun providesPlantRepository(appDatabase:AppDatabase): PlantRepository = PlantRepository(appDatabase)

    @Provides
    @Singleton
    fun provideLayoutInflater( context: Context): LayoutInflater {
        return LayoutInflater.from(context)
    }


}