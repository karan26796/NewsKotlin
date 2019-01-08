package h.app.hackit.newsapp.kotlin.a.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T, L : BaseRecyclerListener, VH : BaseViewHolder<T, L>>
(var mList: ArrayList<T>, var listener: L, var layoutInflater: LayoutInflater) : RecyclerView.Adapter<VH>() {

    val isEmpty: Boolean
        get() = itemCount == 0

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH

    abstract override fun onBindViewHolder(holder: VH, position: Int)

    abstract fun getLayoutID(pos: Int): Int
    override fun getItemCount(): Int {
        return mList.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    fun setItems(mList: MutableList<T>?) {
        if (mList == null) {
            throw IllegalArgumentException("Cannot set `null` item to the Recycler adapter")
        }
        this.mList.clear()
        this.mList.addAll(mList)
        notifyDataSetChanged()
    }

    fun getItem(position: Int): T {
        return mList[position]
    }

    fun add(item: T?) {
        if (item == null) {
            throw IllegalArgumentException("Cannot add null item to the Recycler adapter")
        }
        mList.add(item)
        notifyItemInserted(mList.size - 1)
    }

    fun addAll(mList: List<T>?) {
        if (mList == null) {
            throw IllegalArgumentException("Cannot add `null` mList to the Recycler adapter")
        }
        this.mList.addAll(mList)
        notifyItemRangeInserted(this.mList.size - mList.size, mList.size)
    }

    fun clear() {
        mList.clear()
        notifyDataSetChanged()
    }

    fun remove(item: T) {
        val position = mList.indexOf(item)
        if (position > -1) {
            mList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(hasStableIds)
    }

    @JvmOverloads
    protected fun inflate(@LayoutRes layout: Int, parent: ViewGroup?, attachToRoot: Boolean = false): View {
        return layoutInflater.inflate(layout, parent, attachToRoot)
    }
}