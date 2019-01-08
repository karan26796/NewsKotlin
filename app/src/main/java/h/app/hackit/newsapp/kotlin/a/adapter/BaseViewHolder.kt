package h.app.hackit.newsapp.kotlin.a.adapter

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T, L : BaseRecyclerListener>
(itemView: View, protected var listener: L)
    : RecyclerView.ViewHolder(itemView) {
    var binder: ViewDataBinding = DataBindingUtil.bind(itemView)!!
    abstract fun onBind(item: T)
}