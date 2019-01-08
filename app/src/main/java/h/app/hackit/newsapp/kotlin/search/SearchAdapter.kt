package h.app.hackit.newsapp.kotlin.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import h.app.hackit.newsapp.R
import h.app.hackit.newsapp.kotlin.a.adapter.BaseRecyclerAdapter
import h.app.hackit.newsapp.kotlin.a.adapter.BaseRecyclerListener
import h.app.hackit.newsapp.kotlin.a.adapter.BaseViewHolder
import h.app.hackit.newsapp.kotlin.model.News
import h.app.hackit.newsapp.kotlin.search.SearchAdapter.ItemClickListener
import kotlinx.android.synthetic.main.item_news_search.view.*

class SearchAdapter(mList: ArrayList<News>, listener: ItemClickListener, layoutInflater: LayoutInflater) : BaseRecyclerAdapter<News, ItemClickListener, SearchAdapter.SearchHolder>(mList, listener, layoutInflater) {

    override fun getLayoutID(pos: Int): Int {
        return if (getItemViewType(pos) == 0) 0
        else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        val v = inflate(R.layout.item_news_search, parent, false)
        return SearchHolder(v, listener)
    }

    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        holder.onBind(mList[position])
    }

    interface ItemClickListener : BaseRecyclerListener {
        fun onItemClicked(news: News)
    }

    inner class SearchHolder(itemView: View, listener: ItemClickListener) : BaseViewHolder<News, ItemClickListener>(itemView, listener), View.OnClickListener {
        override fun onClick(v: View?) {
            listener.onItemClicked(mList[adapterPosition])
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onBind(item: News) {
            itemView.textNewsTitle.text = item.title
            itemView.textNewsDate.text = item.date
            itemView.textNewsSource.text = item.source.name
            if (item.urlToImage != "")
                Picasso.get().load(item.urlToImage)
                        .into(itemView.imageNewsHome)
        }
    }
}
