package h.app.hackit.newsapp.customviews

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import h.app.hackit.newsapp.R

class CustomConstraintLayout : ConstraintLayout {
    constructor(context: Context, attributes: AttributeSet) : super(context, attributes) {
        init(context)
    }

    constructor(context: Context) : super(context) {
        init(context)
    }

    private fun init(context: Context) {
        val imageButton = ImageButton(context)
        imageButton.id = 0
        imageButton.layoutParams = ConstraintLayout.LayoutParams(100, 100)
        imageButton.setImageResource(R.drawable.ic_search_black)
        imageButton.setBackgroundColor(android.R.attr.selectableItemBackgroundBorderless)

        addView(imageButton)
    }
}