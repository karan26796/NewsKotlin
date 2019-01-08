package h.app.hackit.newsapp.kotlin.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import h.app.hackit.newsapp.BR
import h.app.hackit.newsapp.R
import h.app.hackit.newsapp.kotlin.a.adapter.BaseRecyclerAdapter
import h.app.hackit.newsapp.kotlin.a.adapter.BaseRecyclerListener
import h.app.hackit.newsapp.kotlin.a.adapter.BaseViewHolder
import h.app.hackit.newsapp.kotlin.model.News
import h.app.hackit.newsapp.kotlin.search.SearchAdapterData.ItemClickListener

class SearchAdapterData(mList: ArrayList<News>, listener: ItemClickListener, layoutInflater: LayoutInflater)
    : BaseRecyclerAdapter<News, ItemClickListener, SearchAdapterData.SearchHolder>(mList, listener, layoutInflater) {

    override fun getLayoutID(pos: Int): Int {
        return if (getItemViewType(pos) == 0) 0
        else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        val v = inflate(R.layout.item_news_search_data, parent, false)
        return SearchHolder(v, listener)
    }

    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        val news: News = mList[position]
        holder.onBind(news)
    }

    interface ItemClickListener : BaseRecyclerListener {
        fun onItemClicked(news: News)
        fun onOptionClicked(news: News)
    }

    inner class SearchHolder(itemView: View, listener: ItemClickListener) : BaseViewHolder<News, ItemClickListener>(itemView, listener), View.OnClickListener {

        override fun onClick(v: View?) {
            if (v == itemView)
                listener.onItemClicked(mList[adapterPosition])
            if (v == itemView.findViewById(R.id.buttonOptions))
                listener.onOptionClicked(mList[adapterPosition])
        }

        init {
            itemView.setOnClickListener(this)
            itemView.findViewById<ImageButton>(R.id.buttonOptions).setOnClickListener(this)
        }

        override fun onBind(item: News) {
            binder.setVariable(BR.item, item)
            binder.executePendingBindings()
        }
    }
}
