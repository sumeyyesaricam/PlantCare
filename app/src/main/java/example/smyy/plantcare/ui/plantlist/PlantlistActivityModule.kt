package example.smyy.plantcare.ui.plantlist

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import example.smyy.plantcare.data.AppDatabase
import example.smyy.plantcare.data.dao.PlantDao
import example.smyy.plantcare.data.repository.PlantRepository
import javax.inject.Singleton

@Module
class PlantlistActivityModule{


    /*@Singleton
    @Provides
    fun provideContext(application: Application) : Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun providesAppDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "my-plant-db").build()

    @Singleton
    @Provides
    fun providesPlantDao(database: AppDatabase) = database.plantDao()

    @Singleton
    @Provides
    fun providesPlantRepository(plantDao: PlantDao) = PlantRepository(plantDao)*/
}