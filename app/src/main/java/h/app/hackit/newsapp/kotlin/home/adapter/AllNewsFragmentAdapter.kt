package h.app.hackit.newsapp.kotlin.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import h.app.hackit.newsapp.kotlin.home.fragments.CategoryFragment
import java.util.*

class AllNewsFragmentAdapter(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {
    private val list = object : ArrayList<String>() {
        init {
            add("Top News")
            add("Business")
            add("Sports")
            add("Health")
            add("Technology")
        }
    }

    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            CategoryFragment.newInstance("in", "general")
        } else CategoryFragment.newInstance("in", list[position].toLowerCase())
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return list[position]
    }

}