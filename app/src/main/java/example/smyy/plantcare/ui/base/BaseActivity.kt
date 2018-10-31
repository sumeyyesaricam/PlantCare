package example.smyy.plantcare.ui.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import dagger.android.AndroidInjection
import example.smyy.plantcare.R
import kotlin.reflect.KClass

abstract class BaseActivity<T: ViewDataBinding> : AppCompatActivity() {

     private lateinit var mViewDataBinding:T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDependencyInjection()
        performDataBinding()
    }

    fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }
    @LayoutRes
    abstract fun getLayoutId(): Int


    fun Activity.gotoActivity(cls: KClass<out Activity>, finish: Boolean = false,
                              extras: Map<String, Any?>? = null) {
        val intent = Intent(this, cls.java)
        extras?.forEach { intent.addExtra(it.key, it.value) }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
        if (finish) finish()
    }

    fun Intent.addExtra(key: String, value: Any?) {
        when (value) {
            is Long -> putExtra(key, value)
            is String -> putExtra(key, value)
            is Boolean -> putExtra(key, value)
            is Float -> putExtra(key, value)
            is Double -> putExtra(key, value)
            is Int -> putExtra(key, value)
            is Parcelable -> putExtra(key, value)
            //Add other types when needed
        }
    }

    inline fun <reified T> Activity.getExtra(extra: String): T? {
        return intent.extras?.get(extra) as? T?
    }
    fun getViewDataBinding(): T {
        return mViewDataBinding
    }
    fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mViewDataBinding.executePendingBindings()
    }
}