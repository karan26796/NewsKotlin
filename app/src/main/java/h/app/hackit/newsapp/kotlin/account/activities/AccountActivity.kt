package h.app.hackit.newsapp.kotlin.account.activities

import android.os.Bundle
import android.view.MenuItem
import h.app.hackit.newsapp.R
import h.app.hackit.newsapp.kotlin.a.activities.BaseActivity
import h.app.hackit.newsapp.kotlin.a.customviews.CustomToolbar
import h.app.hackit.newsapp.kotlin.account.fragments.AccountFragment
import h.app.hackit.newsapp.kotlin.account.fragments.FavoritesFragment
import kotlinx.android.synthetic.main.activity_account.*

class AccountActivity : BaseActivity(), CustomToolbar.ToolbarButtonClickListener
        , AccountFragment.ClickListener {
    override fun onClickBookmark() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.frameAccount, FavoritesFragment.newInstance())
                .commit()
    }

    override fun onLeftBtnClicked() {
        onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home ->
                inflateFragment()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onRightBtnClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        toolbarAccount.setListener(this)
        if (savedInstanceState == null)
            inflateFragment()
    }

    fun inflateFragment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.frameAccount, AccountFragment.newInstance(mFirebaseUser!!))
                .commit()
    }
}