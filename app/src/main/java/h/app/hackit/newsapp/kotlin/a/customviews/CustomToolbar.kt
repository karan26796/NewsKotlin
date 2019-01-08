package h.app.hackit.newsapp.kotlin.a.customviews

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import h.app.hackit.newsapp.R

class CustomToolbar : ConstraintLayout, View.OnClickListener {
    private lateinit var listener: ToolbarButtonClickListener

    fun setListener(listener: ToolbarButtonClickListener) {
        this.listener = listener
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnLeft -> listener.onLeftBtnClicked()
            R.id.btnRight -> listener.onRightBtnClicked()
        }
    }

    interface ToolbarButtonClickListener {
        fun onLeftBtnClicked()

        fun onRightBtnClicked()
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        val a = context.obtainStyledAttributes(
                attrs,
                R.styleable.CustomToolbar)
        val root = LayoutInflater.from(context).inflate(R.layout.toolbar_custom, this)
        val left = root.findViewById<ImageButton>(R.id.btnLeft)
        val right = root.findViewById<ImageButton>(R.id.btnRight)
        val title = root.findViewById<TextView>(R.id.title)
        title.text = a.getString(R.styleable.CustomToolbar_title)
        title.setTextColor(a.getColor(R.styleable.CustomToolbar_titleColor, Color.BLACK))
        left.setImageDrawable(a.getDrawable(R.styleable.CustomToolbar_leftDrawable))
        right.setImageDrawable(a.getDrawable(R.styleable.CustomToolbar_rightDrawable))

        left.setOnClickListener(this)
        right.setOnClickListener(this)
        a.recycle()
    }
}
