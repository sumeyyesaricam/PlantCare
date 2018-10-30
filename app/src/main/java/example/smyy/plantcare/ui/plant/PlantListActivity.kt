package example.smyy.plantcare.ui.plant

import android.os.Bundle
import example.smyy.plantcare.R
import javax.inject.Inject
import android.view.View
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import example.smyy.plantcare.databinding.ActivityPlantListBinding
import example.smyy.plantcare.ui.base.BaseActivity
import example.smyy.plantcare.util.Config.Companion.AddPlantFragment_TAG
import example.smyy.plantcare.util.Config.Companion.PlantListFragment_TAG

class PlantListActivity : BaseActivity<ActivityPlantListBinding>(), HasSupportFragmentInjector {

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun getLayoutId(): Int {
        return R.layout.activity_plant_list
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragment = PlantListFragment()
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment, PlantListFragment_TAG).commit()
    }

    fun onClickAddPlant(view: View) {
        val fragment = AddPlantFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment, AddPlantFragment_TAG)
                .addToBackStack(null).commit()

    }
    fun showFragment(fragment:Fragment,tag:String) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment, tag)
                .addToBackStack(null).commit()

    }
}
