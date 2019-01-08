package h.app.hackit.newsapp.kotlin.webview

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle

import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsServiceConnection
import androidx.browser.customtabs.CustomTabsSession

class CustomTabActivityHelper : ServiceConnectionCallback {

    private var mCustomTabsSession: CustomTabsSession? = null
    private var mCustomTabsClient: CustomTabsClient? = null
    private var mConnection: CustomTabsServiceConnection? = null
    private var mConnectionCallback: CustomTabConnectionCallback? = null

    /**
     * Creates or retrieves an exiting CustomTabsSession.
     *
     * @return a CustomTabsSession.
     */
    val session: CustomTabsSession?
        get() {
            if (mCustomTabsClient == null) {
                mCustomTabsSession = null
            } else if (mCustomTabsSession == null) {
                mCustomTabsSession = mCustomTabsClient!!.newSession(null)
            }
            return mCustomTabsSession
        }

    /**
     * Interface to handle cases where the chrome custom tab cannot be opened.
     */
    interface CustomTabFallback {
        fun openUri(activity: Activity, uri: Uri)
    }

    /**
     * Interface to handle connection callbacks to the custom tab. We'll use this to handle UI changes
     * when the connection is either connected or disconnected.
     */
    interface CustomTabConnectionCallback {
        fun onCustomTabConnected()

        fun onCustomTabDisconnected()
    }


    /**
     * Binds the activity to the custom tabs service.
     *
     * @param activity activity to be "bound" to the service
     */
    fun bindCustomTabsService(activity: Activity) {
        if (mCustomTabsClient != null) return

        val packageName = CustomTabsHelper.getPackageNameToUse(activity) ?: return

        mConnection = ServiceConnection(this)
        CustomTabsClient.bindCustomTabsService(activity, packageName, mConnection)
    }

    /**
     * Unbinds the activity from the custom tabs service
     *
     * @param activity
     */
    fun unbindCustomTabsService(activity: Activity) {
        if (mConnection == null) return
        activity.unbindService(mConnection)
        mCustomTabsClient = null
        mCustomTabsSession = null
        mConnection = null
    }

    /**
     * Register a Callback to be called when connected or disconnected from the Custom Tabs Service.
     *
     * @param connectionCallback
     */
    fun setConnectionCallback(connectionCallback: CustomTabConnectionCallback) {
        this.mConnectionCallback = connectionCallback
    }

    /**
     * @return true if call to mayLaunchUrl was accepted.
     * @see {@link CustomTabsSession.mayLaunchUrl
     */
    fun mayLaunchUrl(uri: Uri, extras: Bundle, otherLikelyBundles: List<Bundle>): Boolean {
        if (mCustomTabsClient == null) return false

        val session = session
        return session != null && session.mayLaunchUrl(uri, extras, otherLikelyBundles)

    }

    override fun onServiceConnected(client: CustomTabsClient) {
        mCustomTabsClient = client
        mCustomTabsClient!!.warmup(0L)
        if (mConnectionCallback != null) {
            mConnectionCallback!!.onCustomTabConnected()
        }
    }

    override fun onServiceDisconnected() {
        mCustomTabsClient = null
        mConnection = null
        if (mConnectionCallback != null) {
            mConnectionCallback!!.onCustomTabDisconnected()
        }
    }

    companion object {


        /**
         * Utility method for opening a custom tab
         *
         * @param activity         Host activity
         * @param customTabsIntent custom tabs intent
         * @param uri              uri to open
         * @param fallback         fallback to handle case where custom tab cannot be opened
         */
        fun openCustomTab(activity: Activity, customTabsIntent: CustomTabsIntent, uri: Uri, fallback: CustomTabFallback?, fallbackToBrowser: Boolean) {
            val packageName = CustomTabsHelper.getPackageNameToUse(activity)
            if (packageName != null) {
                customTabsIntent.intent.setPackage(packageName)
                customTabsIntent.launchUrl(activity, uri)
            } else if (fallback == null || fallbackToBrowser) {
                activity.startActivity(Intent("android.intent.action.VIEW").setData(uri))
            } else {
                fallback.openUri(activity, uri)
            }
        }
    }
}
