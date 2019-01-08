package h.app.hackit.newsapp.kotlin.webview

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

import h.app.hackit.newsapp.R


/**
 * Action Broadcast receiver for the custom chrome tab
 *
 *
 * Created by segun.famisa on 04/06/2016.
 */

class ChromeTabActionBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val data = intent.dataString

        if (data != null) {
            val toastText = getToastText(context, intent.getIntExtra(KEY_ACTION_SOURCE, -1), data)
            Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
        }
    }


    private fun getToastText(context: Context, actionSource: Int, message: String): String {
        when (actionSource) {

        }
        return context.getString(R.string.search)
    }

    companion object {
        val KEY_ACTION_SOURCE = "org.chromium.customtabsdemos.ACTION_SOURCE"
    }
}
