package example.smyy.plantcare.ui.plant

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import example.smyy.plantcare.R
import javax.inject.Inject
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import example.smyy.plantcare.util.Config.Companion.PlantListFragment_TAG
import androidx.fragment.app.FragmentTransaction
import example.smyy.plantcare.util.Config.Companion.PublishedPlantsFragment_TAG


class PlantListActivity : AppCompatActivity(), HasSupportFragmentInjector, ActionBar.TabListener {
    override fun onTabSelected(tab: ActionBar.Tab, ft: FragmentTransaction?) {
        var nTabSelected = tab.position
        when (nTabSelected) {
            0 -> showFragment(PlantListFragment(), PlantListFragment_TAG)
            1 -> showFragment(PublishedPlantsFragment(), PublishedPlantsFragment_TAG)
        }
    }

    override fun onTabUnselected(tab: ActionBar.Tab?, ft: FragmentTransaction?) {

    }

    override fun onTabReselected(tab: ActionBar.Tab?, ft: FragmentTransaction?) {

    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_list)
        AndroidInjection.inject(this)
        val fragment = PlantListFragment()
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment, PlantListFragment_TAG).commit()
        setupTabs()
    }

    private fun showFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment, tag).commit()
    }

    private fun setupTabs() {
        val actionBar = supportActionBar
        actionBar!!.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS)
        val myPlantTab: ActionBar.Tab = actionBar.newTab().setText(R.string.my_plant).setTabListener(this)
        val addplantTab: ActionBar.Tab = actionBar.newTab().setText(R.string.published_plant).setTabListener(this)
        actionBar.addTab(myPlantTab)
        actionBar.addTab(addplantTab)
    }
}
