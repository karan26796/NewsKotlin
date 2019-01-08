package h.app.hackit.newsapp.kotlin.search

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import h.app.hackit.newsapp.R
import h.app.hackit.newsapp.kotlin.a.activities.BaseActivity
import h.app.hackit.newsapp.kotlin.a.customviews.CustomSearchToolbar
import h.app.hackit.newsapp.kotlin.a.utils.Constants
import h.app.hackit.newsapp.kotlin.home.fragments.NewsBottomSheet
import h.app.hackit.newsapp.kotlin.model.News
import h.app.hackit.newsapp.kotlin.model.NewsResponse
import h.app.hackit.newsapp.kotlin.retrofit.NewsApiClient
import h.app.hackit.newsapp.kotlin.retrofit.NewsApiInterface
import h.app.hackit.newsapp.kotlin.retrofit.RetrofitCallBack
import h.app.hackit.newsapp.kotlin.webview.CustomTabActivityHelper
import h.app.hackit.newsapp.kotlin.webview.WebViewFallback
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Response
import java.util.*

class SearchActivity : BaseActivity(), RetrofitCallBack.RetrofitCallbackListener<NewsResponse>,
        SearchAdapterData.ItemClickListener, CustomSearchToolbar.ToolbarButtonClickListener,
        CustomSearchToolbar.EditTextChangeListener {
    private lateinit var newsBottomSheet: NewsBottomSheet
    override fun onOptionClicked(news: News) {
        newsBottomSheet = NewsBottomSheet.newInstance(news)
        if (newsBottomSheet.isVisible) {
            newsBottomSheet.dismiss()
        } else
            newsBottomSheet.show(supportFragmentManager, "")
    }

    override fun onBtnClicked(i: Int) {
        if (i == 1)
            onBackPressed()
    }

    private lateinit var mCustomTabActivityHelper: CustomTabActivityHelper
    private lateinit var mConnectionCallback: CustomTabActivityHelper.CustomTabConnectionCallback

    private fun launchCustomTab(url: String) {
        val intentBuilder = CustomTabsIntent.Builder()
        intentBuilder.setShowTitle(true)
        CustomTabActivityHelper.openCustomTab(this, intentBuilder.build(), Uri.parse(url), WebViewFallback(), false)
    }

    private fun setupCustomTabHelper() {
        this.mCustomTabActivityHelper = CustomTabActivityHelper()
        this.mCustomTabActivityHelper.setConnectionCallback(mConnectionCallback)
    }

    override fun onItemClicked(news: News) {
        launchCustomTab(news.url)
    }

    override fun onSuccess(call: Call<NewsResponse>, response: Response<NewsResponse>) {
        var newsList = ArrayList<News>()
        try {
            assert(response.body() != null)
            newsList = response.body()!!.articles as ArrayList<News>
        } catch (e: NullPointerException) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
        recyclerSearch.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        recyclerSearch.adapter = SearchAdapterData(newsList, this, layoutInflater)
    }

    override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
    }

    lateinit var callBack: RetrofitCallBack<NewsResponse>

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        val text = s.toString()
        if (text == "") {
            error_layout.visibility = View.GONE
        } else if (text != "" && text.length >= 2) {
            error_layout.visibility = View.GONE
            val apiInterface = NewsApiClient.client.create(NewsApiInterface::class.java)
            val responseCall = apiInterface.getNewsSearch(text, Constants.NEWS_API_KEY)
            callBack = RetrofitCallBack(this)
            responseCall.enqueue(callBack)
        }
    }

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        searchToolbar.setBtnClickListener(this)
        searchToolbar.setEditTextListener(this)
    }
}
