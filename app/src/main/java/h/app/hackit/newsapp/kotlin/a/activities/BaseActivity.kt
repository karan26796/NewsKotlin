package h.app.hackit.newsapp.kotlin.a.activities

import android.os.Bundle

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    protected var mFirebaseUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        mFirebaseUser = mAuth.currentUser
    }
}
