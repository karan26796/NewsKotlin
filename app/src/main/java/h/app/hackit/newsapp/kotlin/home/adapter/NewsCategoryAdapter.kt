package h.app.hackit.newsapp.kotlin.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.squareup.picasso.Picasso
import h.app.hackit.newsapp.R
import h.app.hackit.newsapp.kotlin.a.adapter.BaseRecyclerAdapter
import h.app.hackit.newsapp.kotlin.a.adapter.BaseRecyclerListener
import h.app.hackit.newsapp.kotlin.a.adapter.BaseViewHolder
import h.app.hackit.newsapp.kotlin.model.News
import kotlinx.android.synthetic.main.item_news_home.view.*

class NewsCategoryAdapter(mList: ArrayList<News>, listener: ItemClickListener, layoutInflater: LayoutInflater) : BaseRecyclerAdapter<News, NewsCategoryAdapter.ItemClickListener, NewsCategoryAdapter.NewsHolder>(mList, listener, layoutInflater) {

    override fun getLayoutID(pos: Int): Int {
        return if (getItemViewType(pos) == 0) 0
        else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val v = inflate(R.layout.item_news_home, parent, false)
        return NewsHolder(v, listener)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.onBind(mList[position])
    }

    interface ItemClickListener : BaseRecyclerListener {
        fun onItemClicked(newsFeed: News)
    }

    inner class NewsHolder(itemView: View, listener: ItemClickListener) : BaseViewHolder<News, ItemClickListener>(itemView, listener), View.OnClickListener {
        val textTitle: TextView = itemView.findViewById(R.id.textCategoryTitle)
        val textDate: TextView = itemView.findViewById(R.id.textCategoryDate)
        val imgNews: TextView = itemView.findViewById(R.id.imageCategory)

        override fun onClick(v: View?) {
            listener.onItemClicked(mList[adapterPosition])
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onBind(item: News) {
            itemView.textNewsTitle.text = item.title
            itemView.textNewsDate.text = item.date
            Picasso.get().load(item.urlToImage)
                    .into(itemView.imageNewsHome)
        }
    }
}
