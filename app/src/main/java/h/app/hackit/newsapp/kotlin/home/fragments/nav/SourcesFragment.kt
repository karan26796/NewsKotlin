package h.app.hackit.newsapp.kotlin.home.fragments.nav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import h.app.hackit.newsapp.R
import h.app.hackit.newsapp.kotlin.home.fragments.nav.base.NavBaseFragment
import kotlinx.android.synthetic.main.fragment_home.view.*

class SourcesFragment : NavBaseFragment() {

    override fun getLayoutID(): Int {
        return R.layout.fragment_sources
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(getLayoutID(), container, false)
        view.recyclerHome!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        return view
    }

    override fun init() {
    }

}
