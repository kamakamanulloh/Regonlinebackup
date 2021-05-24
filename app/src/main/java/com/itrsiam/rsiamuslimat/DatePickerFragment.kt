package com.itrsiam.rsiamuslimat

import android.app.DatePickerDialog
import android.content.Context
import androidx.fragment.app.Fragment
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [DatePickerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DatePickerFragment(context: Context, isSpinnerType: Boolean = false) {
    private var dialog: DatePickerDialog
    private var callback: Callback? = null
    private val listener =
        DatePickerDialog.OnDateSetListener { datePicker, year, monthOfYear, dayOfMonth ->
            callback?.onDateSelected(dayOfMonth, monthOfYear, year)
        }
    init {
        val style = if (isSpinnerType) R.style.SpinnerDatePickerDialog else 0
        val cal = Calendar.getInstance()
        dialog = DatePickerDialog(context, style, listener,
            cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
    }
    fun showDialog(dayofMonth: Int, month: Int, year: Int, callback: Callback?) {
        this.callback = callback
        dialog.datePicker.init(year, month, dayofMonth, null)
        dialog.show()
    }
    fun setMinDate(minDate: Long) {
        dialog.datePicker.minDate = minDate
    }
    fun setMaxDate(maxDate: Long) {
        dialog.datePicker.maxDate = maxDate
    }
    interface Callback {
        fun onDateSelected(dayofMonth: Int, month: Int, year: Int)
    }
}