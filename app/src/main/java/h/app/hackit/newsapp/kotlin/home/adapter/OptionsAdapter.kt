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

class OptionsAdapter(mList: ArrayList<Option>, listener: OptionsClickListener, layoutInflater: LayoutInflater) : BaseRecyclerAdapter<Option, OptionsClickListener, OptionsAdapter.OptionsHolder>(mList, listener, layoutInflater) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionsHolder {
        val view: View = inflate(getLayoutID(viewType), parent, false)
        return OptionsHolder(view, listener)
    }

    override fun onBindViewHolder(holder: OptionsHolder, position: Int) {
        holder.onBind(mList[position])
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getLayoutID(pos: Int): Int {
        return R.layout.item_options_small
    }

    interface OptionsClickListener : BaseRecyclerListener {
        fun onOptionClicked(option: Option)
    }

    inner class OptionsHolder(itemView: View, listener: OptionsClickListener) :
            BaseViewHolder<Option, OptionsClickListener>(itemView, listener), View.OnClickListener {
        override fun onBind(item: Option) {
            binder.setVariable(BR.option, item)
            binder.executePendingBindings()
        }

        override fun onClick(v: View?) {
            listener.onOptionClicked(mList[adapterPosition])
        }

        init {
            itemView.setOnClickListener(this)
        }
    }
}