package h.app.hackit.newsapp.kotlin.model

import android.graphics.drawable.Drawable
import com.google.firebase.auth.FirebaseUser
import h.app.hackit.newsapp.kotlin.account.adapter.CategoryAdapter

class Account(val type: Int) {
    internal lateinit var adapter: CategoryAdapter
    internal lateinit var title: String
    internal lateinit var user: FirebaseUser
    internal lateinit var text: String
    internal lateinit var drawable: Drawable

    constructor(type: Int, user: FirebaseUser) : this(type) {
        this.user = user
    }

    constructor(type: Int, text: String, title: String, drawable: Drawable) : this(type) {
        this.text = text
        this.drawable = drawable
        this.title = title
    }

    constructor(type: Int, adapter: CategoryAdapter, title: String) : this(type) {
        this.adapter = adapter
        this.title = title
    }
}
