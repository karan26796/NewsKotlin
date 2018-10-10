package h.app.hackit.newsapp.customviews

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import h.app.hackit.newsapp.R

class BenefitView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    init {
        inflate(context, R.layout.benefit_view, this)

        val imageView: ImageView = findViewById(R.id.image)
        val textView: TextView = findViewById(R.id.caption)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.BenefitView)
        imageView.setImageDrawable(attributes.getDrawable(R.styleable.BenefitView_image))

        textView.text = attributes.getString(R.styleable.BenefitView_text)
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, attributes.getDimension(R.styleable.BenefitView_textSize, 0F))
        attributes.recycle()
    }

}