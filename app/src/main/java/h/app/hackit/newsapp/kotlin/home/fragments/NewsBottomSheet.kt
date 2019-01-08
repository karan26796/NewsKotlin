package h.app.hackit.newsapp.kotlin.home.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import h.app.hackit.newsapp.R
import h.app.hackit.newsapp.kotlin.a.utils.ItemDivider
import h.app.hackit.newsapp.kotlin.home.adapter.OptionsAdapter
import h.app.hackit.newsapp.kotlin.model.Favorites
import h.app.hackit.newsapp.kotlin.model.News
import h.app.hackit.newsapp.kotlin.model.Option
import h.app.hackit.newsapp.kotlin.roomdatabase.FavoritesDBHelper
import java.util.*

class NewsBottomSheet : BottomSheetDialogFragment(), OptionsAdapter.OptionsClickListener {
    private lateinit var optionsRecycler: RecyclerView
    private lateinit var optionsList: ArrayList<Option>
    var news: News? = null
    private lateinit var favoritesDBHelper: FavoritesDBHelper
    private val favorites: Favorites = Favorites()

    override fun onOptionClicked(option: Option) {
        when (optionsList[optionsList.indexOf(option)].title) {
            "Add Bookmark" -> {
                val added: Boolean = favoritesDBHelper.newFavorite(favorites)
                if (added) {
                    Toast.makeText(context, "Added to Favorites", Toast.LENGTH_SHORT).show()
                } else
                    Toast.makeText(context, "Added, Not", Toast.LENGTH_SHORT).show()
            }
            "Remove Bookmark" -> {
                val deleted: Boolean = favoritesDBHelper.deleteFavorite(favoritesDBHelper.favoritesList()[favoritesDBHelper.favoritesList().indexOf(favorites)].getId())
                if (deleted) {
                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Deleted, Note", Toast.LENGTH_SHORT).show()
                }
            }
        }
        dismiss()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoritesDBHelper = FavoritesDBHelper(context)
        news = if (arguments != null) arguments!!.getParcelable("bookmark") else null
        favorites.date = news!!.date
        favorites.url = news!!.url
        favorites.imgUrl = news!!.urlToImage
        favorites.desc = news!!.description
        favorites.title = news!!.title
        favorites.source = news!!.source.name
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.layout_bottom_sheet, container, false)
        optionsRecycler = view.findViewById(R.id.options_recycler)
        optionsRecycler.layoutManager = LinearLayoutManager(context)
        optionsRecycler.addItemDecoration(ItemDivider(ContextCompat.getDrawable(context!!, R.drawable.divider),
                50, 50))
        setOptionsRecycler()
        return view
    }


    private fun setOptionsRecycler() {
        assert(news != null)
        optionsList = object : ArrayList<Option>() {
            init {
                if (favoritesDBHelper.favoritesList().contains(favorites))
                    add(Option("Remove Bookmark", context?.let { ContextCompat.getDrawable(it, R.drawable.ic_bookmark_yes) }!!))
                else
                    add(Option("Add Bookmark", context?.let { ContextCompat.getDrawable(it, R.drawable.ic_bookmark) }!!))
                add(Option("Settings", context?.let { ContextCompat.getDrawable(it, R.drawable.ic_settings) }!!))
            }
        }
        val adapter = OptionsAdapter(optionsList, this, layoutInflater)
        optionsRecycler.adapter = adapter
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
        } catch (e: ClassCastException) {
            e.printStackTrace()
        }

    }

    companion object {

        fun newInstance(bookmark: News): NewsBottomSheet {

            val args = Bundle()
            args.putParcelable("bookmark", bookmark)
            val fragment = NewsBottomSheet()
            fragment.arguments = args
            return fragment
        }
    }
}
