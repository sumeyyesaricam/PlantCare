package example.smyy.plantcare.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import example.smyy.plantcare.data.AppDatabase
import example.smyy.plantcare.data.dao.PlantDao
import example.smyy.plantcare.data.repository.PlantRepository
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {


    @Provides
    @Singleton
    fun provideContext(application: Application) : Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun providesAppDatabase(context: Context): AppDatabase = AppDatabase.createPersistentDatabase(context)



}