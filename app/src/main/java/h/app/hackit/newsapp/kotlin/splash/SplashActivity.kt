package h.app.hackit.newsapp.kotlin.splash

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import h.app.hackit.newsapp.kotlin.a.activities.BaseActivity
import h.app.hackit.newsapp.kotlin.home.activities.HomeActivity

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        startActivity(Intent(this, HomeActivity::class.java))
    }
}
