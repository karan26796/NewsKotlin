package h.app.hackit.newsapp.kotlin.home.fragments.nav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.google.android.material.tabs.TabLayout
import h.app.hackit.newsapp.R
import h.app.hackit.newsapp.kotlin.home.adapter.AllNewsFragmentAdapter
import h.app.hackit.newsapp.kotlin.home.fragments.nav.base.NavBaseFragment
import kotlinx.android.synthetic.main.fragment_all_news.view.*
import java.util.*

class AllNews : NavBaseFragment() {
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayoutID(), container, false)
        tabLayout = Objects.requireNonNull<FragmentActivity>(activity).findViewById(R.id.tabsHome)
        view.viewpagerAllNews.adapter = AllNewsFragmentAdapter(activity!!.supportFragmentManager)
        tabLayout.setupWithViewPager(view.viewpagerAllNews)
        view.viewpagerAllNews.offscreenPageLimit = 5
        return view
    }

    override fun getLayoutID(): Int {
        return R.layout.fragment_all_news
    }

    override fun init() {

    }
}
