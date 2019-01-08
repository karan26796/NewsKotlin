package h.app.hackit.newsapp.kotlin.account.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import h.app.hackit.newsapp.R
import h.app.hackit.newsapp.kotlin.account.adapter.AccountRecyclerAdapter
import h.app.hackit.newsapp.kotlin.account.adapter.FavoritesAdapter
import h.app.hackit.newsapp.kotlin.model.Favorites
import h.app.hackit.newsapp.kotlin.roomdatabase.FavoritesDBHelper
import h.app.hackit.newsapp.kotlin.webview.CustomTabActivityHelper
import h.app.hackit.newsapp.kotlin.webview.WebViewFallback
import kotlinx.android.synthetic.main.fragment_favorites.view.*

class FavoritesFragment : Fragment(), AccountRecyclerAdapter.AccountItemClickListener, FavoritesAdapter.ItemClickListener {
    lateinit var adapter: FavoritesAdapter
    override fun onItemClicked(pos: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var mCustomTabActivityHelper: CustomTabActivityHelper
    lateinit var mConnectionCallback: CustomTabActivityHelper.CustomTabConnectionCallback

    protected fun launchCustomTab(url: String) {
        val intentBuilder = CustomTabsIntent.Builder()
        intentBuilder.setShowTitle(true)
        CustomTabActivityHelper.openCustomTab(activity!!, intentBuilder.build(), Uri.parse(url), WebViewFallback(), false)
    }

    override fun onItemClicked(news: Favorites) {
        launchCustomTab(news.getUrl())
    }

    override fun onOptionClicked(news: Favorites) {
        val deleted = favoritesDBHelper.deleteFavorite(news.getId())
        if (deleted) {
            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Deleted, Note", Toast.LENGTH_SHORT).show()
        }
        adapter.notifyItemRemoved(list.indexOf(news))
        adapter.notifyDataSetChanged()
    }

    lateinit var favoritesDBHelper: FavoritesDBHelper

    companion object {
        fun newInstance(): FavoritesFragment {
            val args = Bundle()
            val fragment = FavoritesFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoritesDBHelper = FavoritesDBHelper(context)
    }

    lateinit var list: ArrayList<Favorites>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_favorites, container, false)
        v.recyclerFavorite.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        //v.recyclerFavorite.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        setRecycler()
        adapter = FavoritesAdapter(list, this, layoutInflater)
        v.recyclerFavorite.adapter = adapter
        return v
    }

    private fun setRecycler() {
        list = favoritesDBHelper.favoritesList() as ArrayList<Favorites>
    }
}