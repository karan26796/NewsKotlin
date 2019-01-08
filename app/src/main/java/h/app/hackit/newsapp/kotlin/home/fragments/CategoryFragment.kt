package h.app.hackit.newsapp.kotlin.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.facebook.shimmer.ShimmerFrameLayout
import h.app.hackit.newsapp.R
import h.app.hackit.newsapp.kotlin.a.utils.Constants
import h.app.hackit.newsapp.kotlin.home.adapter.NewsHomeAdapter
import h.app.hackit.newsapp.kotlin.home.fragments.nav.base.NavBaseFragment
import h.app.hackit.newsapp.kotlin.model.News
import h.app.hackit.newsapp.kotlin.model.NewsResponse
import h.app.hackit.newsapp.kotlin.retrofit.NewsApiClient
import h.app.hackit.newsapp.kotlin.retrofit.NewsApiInterface
import h.app.hackit.newsapp.kotlin.retrofit.RetrofitCallBack
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.fragment_category.view.*
import retrofit2.Call
import retrofit2.Response
import java.util.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class CategoryFragment : NavBaseFragment(), RetrofitCallBack.RetrofitCallbackListener<NewsResponse>, NewsHomeAdapter.ItemClickListener
        , SwipeRefreshLayout.OnRefreshListener {
    override fun onRefresh() {
        refreshCategory.isRefreshing = false
    }

    private lateinit var newsBottomSheet: NewsBottomSheet
    override fun onOptionClicked(news: News) {
        newsBottomSheet = NewsBottomSheet.newInstance(news)
        if (newsBottomSheet.isVisible) {
            newsBottomSheet.dismiss()
        } else
            newsBottomSheet.show(childFragmentManager, "")
    }

    lateinit var shimmer: ShimmerFrameLayout

    override fun getLayoutID(): Int {
        return R.layout.fragment_category
    }

    override fun init() {
    }

    private lateinit var country: String
    private lateinit var category: String
    private var bundle: Bundle? = null

    companion object {
        fun newInstance(country: String, category: String): CategoryFragment {
            val args = Bundle()
            args.putString("country", country)
            args.putString("category", category)
            val fragment = CategoryFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bundle = arguments
        category = bundle!!.getString("category")
        country = bundle!!.getString("country")
    }

    private lateinit var callBack: RetrofitCallBack<NewsResponse>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(getLayoutID(), container, false)
        shimmer = view.shimmer_view_container
        view.refreshCategory.setOnRefreshListener(this)
        view.recyclerCategory.layoutManager = LinearLayoutManager(context)
        view.recyclerCategory.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        getNews()
        return view
    }

    private fun getNews() {
        shimmer.startShimmer()
        val apiInterface = NewsApiClient.client.create(NewsApiInterface::class.java)
        val responseCall = apiInterface.getTopHeadlines(country, category, 40, Constants.NEWS_API_KEY)
        callBack = RetrofitCallBack(this)
        responseCall.enqueue(callBack)
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
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
        shimmer.stopShimmer()
        shimmer.visibility = View.GONE
        view!!.recyclerCategory.adapter = NewsHomeAdapter(newsList, this, layoutInflater)
    }

    override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
    }
}
