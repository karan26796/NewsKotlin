package h.app.hackit.newsapp.kotlin.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import h.app.hackit.newsapp.BR
import h.app.hackit.newsapp.R
import h.app.hackit.newsapp.kotlin.a.adapter.BaseRecyclerAdapter
import h.app.hackit.newsapp.kotlin.a.adapter.BaseRecyclerListener
import h.app.hackit.newsapp.kotlin.a.adapter.BaseViewHolder
import h.app.hackit.newsapp.kotlin.home.adapter.NewsHomeAdapter.ItemClickListener
import h.app.hackit.newsapp.kotlin.model.News

class NewsHomeAdapter(mList: ArrayList<News>, listener: ItemClickListener, layoutInflater: LayoutInflater)
    : BaseRecyclerAdapter<News, ItemClickListener, NewsHomeAdapter.NewsHolder>(mList, listener, layoutInflater) {

    override fun getLayoutID(pos: Int): Int {
        return if (getItemViewType(pos) == 0) 0
        else 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> 0
            else -> 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        lateinit var v: View
        when (viewType) {
            0 -> {
                v = inflate(R.layout.item_news_home, parent, false)
            }
            else -> {
                v = inflate(R.layout.item_news_home_small, parent, false)
            }
        }
        return NewsHolder(v, listener)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.onBind(mList[position])
    }

    interface ItemClickListener : BaseRecyclerListener {
        fun onItemClicked(news: News)
        fun onOptionClicked(news: News)
    }

    inner class NewsHolder(itemView: View, listener: ItemClickListener)
        : BaseViewHolder<News, ItemClickListener>(itemView, listener), View.OnClickListener {

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
            binder.setVariable(BR.news, item)
            binder.executePendingBindings()
        }
    }
}
