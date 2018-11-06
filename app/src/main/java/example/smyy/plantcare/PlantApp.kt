package example.smyy.plantcare

import android.app.Activity
import android.app.Application
import com.squareup.leakcanary.LeakCanary
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import example.smyy.plantcare.di.component.DaggerAppComponent
import javax.inject.Inject

class PlantApp : Application(), HasActivityInjector  {

    override fun activityInjector(): DispatchingAndroidInjector<Activity> = mAndroidInjector

    @Inject
    lateinit var mAndroidInjector: DispatchingAndroidInjector<Activity>

    private fun initializeLeakDetection() {
        if (BuildConfig.DEBUG) LeakCanary.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
                .app(this)
                .build()
                .inject(this)
        initializeLeakDetection()

    }

}