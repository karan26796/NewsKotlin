package h.app.hackit.newsapp.kotlin.account.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseUser
import h.app.hackit.newsapp.R
import h.app.hackit.newsapp.kotlin.account.adapter.AccountRecyclerAdapter
import h.app.hackit.newsapp.kotlin.model.Account
import h.app.hackit.newsapp.kotlin.roomdatabase.FavoritesDBHelper
import kotlinx.android.synthetic.main.fragment_account.view.*

class AccountFragment : Fragment(), AccountRecyclerAdapter.AccountItemClickListener {
    lateinit var user: FirebaseUser
    lateinit var favoritesDBHelper: FavoritesDBHelper
    lateinit var clickListener: ClickListener

    interface ClickListener {
        fun onClickBookmark()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            clickListener = context as ClickListener
        } catch (c: ClassCastException) {
            Toast.makeText(context, c.message, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun newInstance(user: FirebaseUser): AccountFragment {
            val args = Bundle()
            args.putParcelable("user", user)
            val fragment = AccountFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onItemClicked(pos: Int) {
        when (pos) {
            1 -> clickListener.onClickBookmark()
        }
        Toast.makeText(context, pos.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoritesDBHelper = FavoritesDBHelper(context)
        if (arguments != null)
            user = arguments!!.getParcelable("user") as FirebaseUser
    }

    lateinit var list: ArrayList<Account>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_account, container, false)
        v.recyclerAccount.layoutManager = LinearLayoutManager(context)
        setRecycler()
        v.recyclerAccount.adapter = AccountRecyclerAdapter(list, user, this, layoutInflater)
        return v
    }

    private fun setRecycler() {
        list = ArrayList()
        list.add(Account(0, user))
        list.add(Account(1, favoritesDBHelper.favoritesList().size.toString(),
                "Bookmarks", context?.getDrawable(R.drawable.ic_bookmark)!!))
    }

}