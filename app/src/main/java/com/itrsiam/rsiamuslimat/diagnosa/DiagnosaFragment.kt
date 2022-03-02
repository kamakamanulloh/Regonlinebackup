package com.itrsiam.rsiamuslimat.diagnosa

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.itrsiam.rsiamuslimat.DatePickerFragment
import com.itrsiam.rsiamuslimat.R
import com.itrsiam.rsiamuslimat.api.Utils
import com.itrsiam.rsiamuslimat.kartu.EkartuAdapter
import com.itrsiam.rsiamuslimat.kartu.EkartuResultItem
import com.itrsiam.rsiamuslimat.kartu.KartuPresenter
import com.itrsiam.rsiamuslimat.kartu.KartuView
import com.itrsiam.rsiamuslimat.lupa_rm.LupaRm
import kotlinx.android.synthetic.main.add_rm.view.*
import kotlinx.android.synthetic.main.fragment_diagnosa.*

import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.alert
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DiagnosaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DiagnosaFragment : Fragment(),DiagnosaView, KartuView {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var kartuPresenter: KartuPresenter
    private lateinit var progressDialog : ProgressDialog
    lateinit var diagnosPresenter: DiagnosPresenter
    lateinit var diagnosaAdapter: DiagnosaAdapter
    var id_cust_usr: String? =null
    var dialog: AlertDialog.Builder? = null
    var inflater: LayoutInflater? = null
    lateinit var datePicker: DatePickerFragment
    var tanggal_lahir: String? =null
    private lateinit var ekartuAdapter: EkartuAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_diagnosa, container, false)
        progressDialog = ProgressDialog(requireContext())
        datePicker = DatePickerFragment(requireContext(), true)
        kartuPresenter= KartuPresenter(this)
        kartuPresenter.ekartu(Utils.user_id.toString())
        diagnosPresenter= DiagnosPresenter(this)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_rm.onClick {
            formRm()
        }
        btn_cari.onClick {
            progressDialog.setMessage("Application is loading, please wait")
            progressDialog.show()
            diagnosPresenter.list_diagnosa(id_cust_usr)
        }
    }
    private fun formRm() {
        dialog=AlertDialog.Builder(requireContext())
        inflater=layoutInflater
        val dialogView=inflater?.inflate(R.layout.add_rm,null)
        dialog?.setView(dialogView)
        dialog?.setCancelable(true)
        dialogView?.btn_add?.text="Cari"
        dialogView?.btn_tlahir?.onClick {

            val cal = Calendar.getInstance()
            val d = cal.get(Calendar.DAY_OF_MONTH)
            val m = cal.get(Calendar.MONTH)
            val y = cal.get(Calendar.YEAR)
            datePicker.showDialog(d, m, y, object : DatePickerFragment.Callback {

                override fun onDateSelected(dayofMonth: Int, month: Int, year: Int) {
                    val dayStr = if (dayofMonth < 10) "0${dayofMonth}" else "${dayofMonth}"
                    val mon = month + 1
                    val monthStr = if (mon < 10) "0${mon}" else "${mon}"
                    dialogView?.tv_tl.text= ("$dayStr-$mon-$year")
                    tanggal_lahir = "$year-$monthStr-$dayStr"
                }
            })
        }
        dialogView?.btn_add?.onClick {
            if (dialogView.et_norm.text.isEmpty()|| tanggal_lahir==null){
                dialogView.et_norm.error="No RM atau Tanggal Lahir Harus Diisi"
            } else{
                progressDialog.setMessage("Application is loading, please wait")
                progressDialog.show()

            }


        }
        dialogView?.tv_luparm?.onClick {

            val lupaRm = LupaRm()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment, lupaRm)
                ?.addToBackStack(null)
                ?.commit()




        }
        dialog!!.show()




    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DiagnosaFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DiagnosaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onSuccessDiagnosa(data: List<ResultItem?>?) {
        progressDialog.dismiss()
        rv.adapter=DiagnosaAdapter(data as List<ResultItem>)

    }

    override fun onFailedDiagnosa(msg: String?) {
        alert {
            message=msg.toString()
        }.show()
        progressDialog.dismiss()
    }

    override fun onSuccessAdd(msg: String?) {

    }

    override fun onSuccessDel(msg: String?) {

    }

    override fun onFailedAdd(msg: String?) {

    }

    override fun onSuccessGet(data: List<EkartuResultItem?>?) {
        progressDialog.dismiss()
        spinner_rm.adapter= EkartuAdapter(requireContext(), data as List<EkartuResultItem>)
        ekartuAdapter= EkartuAdapter(requireContext(), data)
        spinner_rm.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            @SuppressLint("SetTextI18n")
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                id_cust_usr=data[position].custUsrId
                tvpasien.text=data[position].custUsrKode+" "+data[position].custUsrNama




            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }

    override fun onFailureAdd(msg: String?) {
        alert {
            message=msg.toString()
        }.show()
        progressDialog.dismiss()
    }
}