package example.smyy.plantcare.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import dagger.android.AndroidInjection
import example.smyy.plantcare.R

abstract class BaseActivity<T: ViewDataBinding> : AppCompatActivity() {

     lateinit var mViewDataBinding:T

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


    fun getViewDataBinding(): T {
        return mViewDataBinding
    }
    fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mViewDataBinding.executePendingBindings()
    }
}