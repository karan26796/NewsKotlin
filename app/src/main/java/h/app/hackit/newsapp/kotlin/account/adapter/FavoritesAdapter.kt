package h.app.hackit.newsapp.kotlin.account.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import h.app.hackit.newsapp.BR
import h.app.hackit.newsapp.R
import h.app.hackit.newsapp.kotlin.a.adapter.BaseRecyclerAdapter
import h.app.hackit.newsapp.kotlin.a.adapter.BaseRecyclerListener
import h.app.hackit.newsapp.kotlin.a.adapter.BaseViewHolder
import h.app.hackit.newsapp.kotlin.model.Favorites

class FavoritesAdapter(mList: ArrayList<Favorites>, listener: ItemClickListener, layoutInflater: LayoutInflater)
    : BaseRecyclerAdapter<Favorites, FavoritesAdapter.ItemClickListener, FavoritesAdapter.FavoritesHolder>(mList, listener, layoutInflater) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesHolder {
        lateinit var v: View

        v = inflate(R.layout.item_favorite, parent, false)
        return FavoritesHolder(v, listener)
    }

    override fun onBindViewHolder(holder: FavoritesHolder, position: Int) {
        holder.onBind(mList[position])
    }

    interface ItemClickListener : BaseRecyclerListener {
        fun onItemClicked(news: Favorites)
        fun onOptionClicked(news: Favorites)
    }

    inner class FavoritesHolder(itemView: View, listener: ItemClickListener)
        : BaseViewHolder<Favorites, ItemClickListener>(itemView, listener), View.OnClickListener {

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

        override fun onBind(item: Favorites) {
            binder.setVariable(BR.favorite, item)
            binder.executePendingBindings()
        }
    }
}
