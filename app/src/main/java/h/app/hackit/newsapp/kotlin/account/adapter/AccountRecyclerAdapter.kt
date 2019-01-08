package h.app.hackit.newsapp.kotlin.account.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseUser
import h.app.hackit.newsapp.BR
import h.app.hackit.newsapp.R
import h.app.hackit.newsapp.kotlin.a.adapter.BaseRecyclerAdapter
import h.app.hackit.newsapp.kotlin.a.adapter.BaseRecyclerListener
import h.app.hackit.newsapp.kotlin.a.adapter.BaseViewHolder
import h.app.hackit.newsapp.kotlin.account.adapter.AccountRecyclerAdapter.AccountItemClickListener
import h.app.hackit.newsapp.kotlin.model.Account

class AccountRecyclerAdapter(mList: ArrayList<Account>, private val user: FirebaseUser, listener: AccountItemClickListener, layoutInflater: LayoutInflater) :
        BaseRecyclerAdapter<Account, AccountItemClickListener, AccountRecyclerAdapter.AccountHolder>(mList, listener, layoutInflater) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountHolder {
        val view: View = inflate(getLayoutID(viewType), parent, false)
        return when (viewType) {
            0 -> UserHolder(view, listener)
            1 -> CategoryHolder(view, listener)
            else -> UserHolder(view, listener)
        }
    }

    override fun onBindViewHolder(holder: AccountHolder, position: Int) {
        holder.onBind(mList[position])
    }

    override fun getLayoutID(pos: Int): Int {
        return when (getItemViewType(pos)) {
            0 -> R.layout.item_account
            1 -> R.layout.item_account_recycler
            else -> -1
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (mList[position].type) {
            0 -> 0
            1 -> 1
            else -> -1
        }
    }

    interface AccountItemClickListener : BaseRecyclerListener {
        fun onItemClicked(pos: Int)
    }

    inner class CategoryHolder(itemView: View, listener: AccountItemClickListener) : AccountHolder(itemView, listener) {
        override fun onClick(v: View?) {
            listener.onItemClicked(1)
        }

        override fun onBind(item: Account) {
            binder.setVariable(BR.category, item)
            binder.executePendingBindings()
        }
    }

    inner class UserHolder(itemView: View, listener: AccountItemClickListener) : AccountHolder(itemView, listener) {
        override fun onClick(v: View?) {
            listener.onItemClicked(0)
        }

        override fun onBind(item: Account) {
            binder.setVariable(BR.account, item)
            binder.executePendingBindings()
        }

    }

    abstract inner class AccountHolder(itemView: View, listener: AccountItemClickListener)
        : BaseViewHolder<Account, AccountItemClickListener>(itemView, listener), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }
    }

}