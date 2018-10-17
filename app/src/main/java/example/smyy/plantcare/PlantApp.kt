package example.smyy.plantcare

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import example.smyy.plantcare.di.component.DaggerAppComponent
import example.smyy.plantcare.di.module.AppModule
import javax.inject.Inject

 class PlantApp : Application(), HasActivityInjector {

     override fun activityInjector(): DispatchingAndroidInjector<Activity> = mAndroidInjector

     @Inject
     lateinit var mAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
                .app(this)
                .build()
                .inject(this)

    }

}