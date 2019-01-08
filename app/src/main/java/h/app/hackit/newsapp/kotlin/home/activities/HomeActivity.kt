package h.app.hackit.newsapp.kotlin.home.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import h.app.hackit.newsapp.R
import h.app.hackit.newsapp.kotlin.a.activities.BaseActivity
import h.app.hackit.newsapp.kotlin.a.customviews.CustomToolbar
import h.app.hackit.newsapp.kotlin.account.fragments.FavoritesFragment
import h.app.hackit.newsapp.kotlin.home.fragments.AccountBottomSheet
import h.app.hackit.newsapp.kotlin.home.fragments.nav.AllNews
import h.app.hackit.newsapp.kotlin.home.fragments.nav.HomeFragment
import h.app.hackit.newsapp.kotlin.search.SearchActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity(), CustomToolbar.ToolbarButtonClickListener {
    private lateinit var accountBottomSheet: AccountBottomSheet
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var fragmentManager: FragmentManager

    override fun onRightBtnClicked() {
        val email: String = firebaseAuth.currentUser!!.email.toString()
        accountBottomSheet = AccountBottomSheet.newInstance(email)
        if (accountBottomSheet.isVisible) {
            accountBottomSheet.dismiss()
        } else
            accountBottomSheet.show(supportFragmentManager, "")
    }

    override fun onLeftBtnClicked() {
        startActivity(Intent(this, SearchActivity::class.java))
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                if (getCurrentFragment() !is HomeFragment)
                    inflateFragment(HomeFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                if (getCurrentFragment() !is AllNews)
                    inflateFragment(AllNews())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                if (getCurrentFragment() !is FavoritesFragment)
                    inflateFragment(FavoritesFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun getCurrentFragment(): Fragment {
        return fragmentManager.findFragmentById(R.id.frameMain)!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        fragmentManager = supportFragmentManager
        firebaseAuth = FirebaseAuth.getInstance()
        if (savedInstanceState == null)
            inflateFragment(HomeFragment())

        tabsHome.visibility = View.GONE
        toolbarMain.setListener(this)
        navigation?.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun inflateFragment(fragment: Fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.frameMain, fragment)
                .commit()
        if (fragment is AllNews) {
            tabsHome.visibility = View.VISIBLE
        } else tabsHome.visibility = View.GONE
    }
}
