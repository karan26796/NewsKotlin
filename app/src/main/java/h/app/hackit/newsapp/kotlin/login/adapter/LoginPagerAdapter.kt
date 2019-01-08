package h.app.hackit.newsapp.kotlin.login.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import h.app.hackit.newsapp.kotlin.login.fragments.SignInFragment
import h.app.hackit.newsapp.kotlin.login.fragments.SignUpFragment

class LoginPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return if (position == 0) SignInFragment.newInstance() else SignUpFragment.newInstance()
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 0) "Sign In" else "Sign Up"
    }
}
