package h.app.hackit.newsapp.kotlin.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import h.app.hackit.newsapp.R
import h.app.hackit.newsapp.kotlin.a.activities.BaseActivity
import h.app.hackit.newsapp.kotlin.home.activities.HomeActivity
import h.app.hackit.newsapp.kotlin.login.adapter.LoginPagerAdapter
import h.app.hackit.newsapp.kotlin.login.fragments.base.BaseFragment
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), BaseFragment.LoginListener {
    override fun onLoginStart() {
        progressLogin.visibility = View.VISIBLE
    }

    override fun onLoginEnd() {
        progressLogin.visibility = View.GONE
    }

    override fun onStart() {
        super.onStart()
        if (mFirebaseUser != null) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbarLogin)

        setViewPager()
    }

    private fun setViewPager() {
        pagerLogin.adapter = LoginPagerAdapter(supportFragmentManager)
        tabLogin.setupWithViewPager(pagerLogin)
    }
}
