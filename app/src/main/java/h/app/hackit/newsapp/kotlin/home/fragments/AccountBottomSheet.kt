package h.app.hackit.newsapp.kotlin.home.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import h.app.hackit.newsapp.R
import h.app.hackit.newsapp.kotlin.a.utils.ItemDivider
import h.app.hackit.newsapp.kotlin.account.activities.AccountActivity
import h.app.hackit.newsapp.kotlin.home.adapter.OptionsAdapter
import h.app.hackit.newsapp.kotlin.model.Option
import kotlinx.android.synthetic.main.dialog_alert.view.*
import java.util.*

class AccountBottomSheet : BottomSheetDialogFragment(), OptionsAdapter.OptionsClickListener {
    private lateinit var optionsRecycler: RecyclerView
    private lateinit var optionsList: ArrayList<Option>
    lateinit var listener: DialogBoxClickListener
    internal var email: String? = null

    override fun onOptionClicked(option: Option) {
        when (optionsList.indexOf(option)) {
            0 -> {
                startActivity(Intent(context, AccountActivity::class.java))
                dismiss()
            }
            1 -> {
                val alertDialog = AlertDialog.Builder(context)
                val alertLayout = layoutInflater
                        .inflate(R.layout.dialog_alert, null)
                alertDialog.setView(alertLayout)
                alertLayout.btn_yes.setOnClickListener { listener.onLogout() }
                alertLayout.btn_no.setOnClickListener { dismiss() }
                alertDialog.create()
                alertDialog.show()
            }
            2 -> dismiss()
        }
    }

    interface DialogBoxClickListener {
        fun onLogout()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        email = if (arguments != null) arguments!!.getString("email") else null

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.layout_bottom_sheet, container, false)
        optionsRecycler = view.findViewById(R.id.options_recycler)
        optionsRecycler.layoutManager = LinearLayoutManager(context)
        optionsRecycler.addItemDecoration(ItemDivider(ContextCompat.getDrawable(context!!, R.drawable.divider),
                50, 50))
        setOptionsRecycler()
        return view
    }


    private fun setOptionsRecycler() {
        assert(email != null)
        optionsList = object : ArrayList<Option>() {
            init {
                add(Option(email.toString(), context?.let { ContextCompat.getDrawable(it, R.drawable.ic_account_circle_dark) }!!))
                add(Option("Logout", context?.let { ContextCompat.getDrawable(it, R.drawable.ic_logout) }!!))
                add(Option("Settings", context?.let { ContextCompat.getDrawable(it, R.drawable.ic_settings) }!!))
            }
        }
        val adapter = OptionsAdapter(optionsList, this, layoutInflater)
        optionsRecycler.adapter = adapter
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            listener = (context as DialogBoxClickListener?)!!
        } catch (e: ClassCastException) {
            e.printStackTrace()
        }

    }

    companion object {

        fun newInstance(email: String): AccountBottomSheet {

            val args = Bundle()
            args.putString("email", email)
            val fragment = AccountBottomSheet()
            fragment.arguments = args
            return fragment
        }
    }
}
