package com.itrsiam.rsiamuslimat.jadwal_dokter

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import butterknife.ButterKnife
import com.itrsiam.rsiamuslimat.DatePickerFragment

import com.itrsiam.rsiamuslimat.R
import com.itrsiam.rsiamuslimat.poli.ListPoliPresenter
import com.itrsiam.rsiamuslimat.poli.ListPoliView
import com.itrsiam.rsiamuslimat.poli.PoliAdapter
import com.itrsiam.rsiamuslimat.poli.PoliResultItem
import kotlinx.android.synthetic.main.fragment_jadwal.*

import org.jetbrains.anko.alert

import org.jetbrains.anko.sdk27.coroutines.onClick
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
@Suppress("DEPRECATION")
class JadwalFragment : Fragment(),ListPoliView,JadwalView {
    private lateinit var poliAdapter: PoliAdapter
    private lateinit var listpolipresenter: ListPoliPresenter
    private lateinit var jadwalPresenter: JadwalPresenter
    var tanggal: String? =null
    var poli_id: String? =null
    lateinit var datePicker: DatePickerFragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_jadwal, container, false)
        ButterKnife.bind(this, view)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listpolipresenter= ListPoliPresenter(this)
        listpolipresenter.getPoli()
        datePicker = DatePickerFragment(requireContext(), true)
        jadwalPresenter= JadwalPresenter(this)
        btntgl.onClick {
            showdate()
        }
        btn_cari.onClick {
            jadwalPresenter.getJadwal(poli_id.toString(),tanggal.toString())
        }

    }
    private fun showdate() {
        val cal = Calendar.getInstance()
        val d = cal.get(Calendar.DAY_OF_MONTH)
        val m = cal.get(Calendar.MONTH)
        val y = cal.get(Calendar.YEAR)
        datePicker.showDialog(d, m, y, object : DatePickerFragment.Callback {
            @SuppressLint("SetTextI18n")
            override fun onDateSelected(dayofMonth: Int, month: Int, year: Int) {
                val dayStr = if (dayofMonth < 10) "0${dayofMonth}" else "${dayofMonth}"
                val mon = month + 1
                val monthStr = if (mon < 10) "0${mon}" else "${mon}"
                tvtgl.text = ("$dayStr-$mon-$year")
                tanggal = "$year-$mon-$dayofMonth"
            }
        })
    }

    override fun onSuccessGet(data: List<PoliResultItem?>?) {
        list_poli.adapter=PoliAdapter(requireContext(), data as List<PoliResultItem>)
        poliAdapter=PoliAdapter(requireContext(), data as List<PoliResultItem>)

        list_poli.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                poli_id =data[position].poliId.toString()


            }
        }
    }

    override fun onFailedGet(msg: String) {

    }

    override fun onGetJadwal(data: List<JadwalResultItem?>?) {
        rv_jadwal.adapter=JadwalListAdapter(data as List<JadwalResultItem>,object :JadwalListAdapter.onClickItem{
            override fun clicked(item: JadwalResultItem?) {

            }

        })

    }

    override fun onFailedGetJadwal(msg: String) {
        requireContext().alert {
            message="gagal"
        }.show()

    }


}
