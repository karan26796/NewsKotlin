package h.app.hackit.newsapp.kotlin.a.customviews

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import h.app.hackit.newsapp.R
import kotlinx.android.synthetic.main.toolbar_search.view.*

class CustomSearchToolbar : ConstraintLayout, View.OnClickListener, TextWatcher {
    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        editTextChangeListener.onTextChanged(s, start, before, count)
    }

    private lateinit var btnClickListener: ToolbarButtonClickListener
    private lateinit var editTextChangeListener: EditTextChangeListener

    fun setBtnClickListener(btnClickListener: ToolbarButtonClickListener) {
        this.btnClickListener = btnClickListener
    }

    fun setEditTextListener(editTextChangeListener: EditTextChangeListener) {
        this.editTextChangeListener = editTextChangeListener
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnLeft -> {
                if (searchBox.text.toString() != "") {
                    searchBox.setText("")
                    btnClickListener.onBtnClicked(0)
                } else btnClickListener.onBtnClicked(1)
            }
        }
    }

    interface EditTextChangeListener {
        fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)
    }

    interface ToolbarButtonClickListener {
        fun onBtnClicked(i: Int)
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        val a = context.obtainStyledAttributes(
                attrs,
                R.styleable.CustomSearchToolbar)
        val root = LayoutInflater.from(context).inflate(R.layout.toolbar_search, this)
        val left = root.findViewById<ImageButton>(R.id.btnLeft)
        val searchField = root.findViewById<EditText>(R.id.searchBox)

        left.setOnClickListener(this)
        searchField.addTextChangedListener(this)
        a.recycle()
    }
}
