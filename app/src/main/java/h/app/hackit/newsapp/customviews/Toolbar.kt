package h.app.hackit.newsapp.customviews

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageButton
import androidx.appcompat.widget.Toolbar

class Toolbar : Toolbar {
    val btnLeft: ImageButton? = null
    val btnRight: ImageButton? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    fun setImageBtns(imgBtn1: Int, imgBtn2: Int) {
        btnLeft?.setImageResource(imgBtn1)
        btnRight?.setImageResource(imgBtn2)
    }

    fun setBtns() {

    }
}