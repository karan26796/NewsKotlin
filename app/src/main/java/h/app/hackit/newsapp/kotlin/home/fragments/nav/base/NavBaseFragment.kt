package h.app.hackit.newsapp.kotlin.home.fragments.nav.base

import android.net.Uri
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import h.app.hackit.newsapp.kotlin.webview.CustomTabActivityHelper
import h.app.hackit.newsapp.kotlin.webview.WebViewFallback


abstract class NavBaseFragment : Fragment() {
    abstract fun getLayoutID(): Int
    abstract fun init()
    private lateinit var mCustomTabActivityHelper: CustomTabActivityHelper
    lateinit var mConnectionCallback: CustomTabActivityHelper.CustomTabConnectionCallback

    protected fun launchCustomTab(url: String) {
        val intentBuilder = CustomTabsIntent.Builder()
        intentBuilder.setShowTitle(true)
        CustomTabActivityHelper.openCustomTab(activity!!, intentBuilder.build(), Uri.parse(url), WebViewFallback(), false)
    }

    private fun setupCustomTabHelper() {
        this.mCustomTabActivityHelper = CustomTabActivityHelper()
        this.mCustomTabActivityHelper.setConnectionCallback(mConnectionCallback)
    }
}