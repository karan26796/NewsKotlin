package h.app.hackit.newsapp.kotlin.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import h.app.hackit.newsapp.BR
import h.app.hackit.newsapp.R
import h.app.hackit.newsapp.kotlin.a.adapter.BaseRecyclerAdapter
import h.app.hackit.newsapp.kotlin.a.adapter.BaseRecyclerListener
import h.app.hackit.newsapp.kotlin.a.adapter.BaseViewHolder
import h.app.hackit.newsapp.kotlin.home.adapter.OptionsAdapter.OptionsClickListener
import h.app.hackit.newsapp.kotlin.model.Option

class SourcesAdapter(mList: ArrayList<Option>, listener: SourcesClickListener, layoutInflater: LayoutInflater) : BaseRecyclerAdapter<Option, SourcesAdapter.SourcesClickListener, SourcesAdapter.SourcesHolder>(mList, listener, layoutInflater) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourcesHolder {
        val view: View = inflate(getLayoutID(viewType), parent, false)
        return SourcesHolder(view, listener)
    }

    override fun onBindViewHolder(holder: SourcesHolder, position: Int) {
        holder.onBind(mList[position])
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getLayoutID(pos: Int): Int {
        return R.layout.item_options_small
    }

    interface SourcesClickListener : BaseRecyclerListener {
        fun onSourceClicked(option: Option)
    }

    inner class SourcesHolder(itemView: View, listener: SourcesClickListener) :
            BaseViewHolder<Option, SourcesClickListener>(itemView, listener), View.OnClickListener {
        override fun onBind(item: Option) {
            binder.setVariable(BR.option, item)
            binder.executePendingBindings()
        }

        override fun onClick(v: View?) {
            listener.onSourceClicked(mList[adapterPosition])
        }

        init {
            itemView.setOnClickListener(this)
        }
    }
}