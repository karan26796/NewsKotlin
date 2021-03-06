package h.app.hackit.newsapp.kotlin.login.fragments.base

import android.content.Context
import android.os.Bundle
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth

abstract class BaseFragment : Fragment(), View.OnClickListener, TextWatcher {
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var loginListener: LoginListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
    }

    interface LoginListener {
        fun onLoginStart()
        fun onLoginEnd()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            loginListener = context as LoginListener
        } catch (e: ClassCastException) {
            e.printStackTrace()
        }
    }

    abstract fun onButtonClicked()

    abstract fun init()

    abstract fun getLayoutID(): Int

    override fun onClick(v: View?) {
        onButtonClicked()
    }
}