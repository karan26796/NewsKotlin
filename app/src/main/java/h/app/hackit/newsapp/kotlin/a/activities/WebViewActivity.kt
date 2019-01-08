package h.app.hackit.newsapp.kotlin.a.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import h.app.hackit.newsapp.R

class WebViewActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var url: String
    internal var id: Int = 0
    internal var bundle: Bundle? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_web_view)

        webView = findViewById(R.id.webView)
        webView.settings.setSupportZoom(true)
        webView.settings.builtInZoomControls = true
        webView.settings.displayZoomControls = true
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = MyBrowser()

        webView.loadUrl(url)

    }

    private inner class MyBrowser : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            if (!url.startsWith("https://") || url.startsWith("http://")) {
                return false
            }
            this@WebViewActivity.startActivity(Intent("android.intent.action.VIEW", Uri.parse(url)))
            return true
        }

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}
