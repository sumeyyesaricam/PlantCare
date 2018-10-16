package example.smyy.plantcare.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import dagger.android.DaggerApplication
import example.smyy.plantcare.di.ForApplication
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule(private  val application: Application){

    @Provides
    @Singleton
    @ForApplication
    fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    @Named("something")
    fun provideSomething(): String = "something"
}