package h.app.hackit.newsapp.kotlin.webview

import android.app.Activity
import android.content.Intent
import android.net.Uri
import h.app.hackit.newsapp.kotlin.a.activities.WebViewActivity

/**
 * Created by karan on 4/1/2018.
 */

class WebViewFallback : CustomTabActivityHelper.CustomTabFallback {

    override fun openUri(activity: Activity, uri: Uri) {
        val webViewIntent = Intent(activity, WebViewActivity::class.java)
        webViewIntent.putExtra(EXTRA_URL, uri.toString())
        activity.startActivity(webViewIntent)
    }

    companion object {
        private val EXTRA_URL = "extra.url"
    }
}
