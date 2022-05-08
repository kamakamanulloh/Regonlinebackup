package com.itrsiam.rsiamuslimat.kartu

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import com.itrsiam.rsiamuslimat.DatePickerFragment
import com.itrsiam.rsiamuslimat.R
import com.itrsiam.rsiamuslimat.api.Utils
import com.itrsiam.rsiamuslimat.lupa_rm.LupaRm
import com.itrsiam.rsiamuslimat.poli.PoliAdapter
import kotlinx.android.synthetic.main.add_rm.view.*
import kotlinx.android.synthetic.main.ekartu.*
import kotlinx.android.synthetic.main.fragment_kartu.*
import kotlinx.android.synthetic.main.pasien_umum_fragment.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.alert
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [KartuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class KartuFragment : Fragment(),KartuView {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var flag = true
    var dialog: AlertDialog.Builder? = null
    var inflater: LayoutInflater? = null
    private lateinit var ekartuAdapter: EkartuAdapter
    lateinit var kartuPresenter: KartuPresenter
    private lateinit var progressDialog : ProgressDialog
    lateinit var datePicker: DatePickerFragment
    var tanggal_lahir: String? =null
    var no_rm: String? =null
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
        return inflater.inflate(R.layout.fragment_kartu, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        kartuPresenter= KartuPresenter(this)
        kartuPresenter.ekartu(Utils.user_id.toString())
        progressDialog = ProgressDialog(requireContext())
        progressDialog.setMessage("Application is loading, please wait")
        progressDialog.show()
        datePicker = DatePickerFragment(requireContext(), true)
        btn_hapus.onClick {
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage("Apakah Anda Yakin Untuk Hapus Kartu No RM $no_rm")
                .setPositiveButton(R.string.ya,
                    DialogInterface.OnClickListener { dialog, id ->
                        // FIRE ZE MISSILES!
                       kartuPresenter.hapus_kartu(no_rm.toString(),Utils.user_id.toString())

                    })
                .setNegativeButton(R.string.batal,
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog

                        dialog.dismiss()
                    })
            // Create the AlertDialog object and return it
            builder.create()
            builder.show()

        }

        add_fab.onClick {
            dialog = AlertDialog.Builder(requireContext())
            inflater = layoutInflater
            val  dialogView = inflater!!.inflate(R.layout.add_rm, null)
//        val mDialogView = LayoutInflater.from(requireContext()).inflate(R.layout.item_detail_dokter, null)
            dialog!!.setView(dialogView)
            dialog!!.setCancelable(true)
            dialog!!.setTitle("Tambahkan RM ke E-card")
            dialogView.btn_tlahir.onClick {

                val cal = Calendar.getInstance()
                val d = cal.get(Calendar.DAY_OF_MONTH)
                val m = cal.get(Calendar.MONTH)
                val y = cal.get(Calendar.YEAR)
                datePicker.showDialog(d, m, y, object : DatePickerFragment.Callback {

                    override fun onDateSelected(dayofMonth: Int, month: Int, year: Int) {
                        val dayStr = if (dayofMonth < 10) "0${dayofMonth}" else "${dayofMonth}"
                        val mon = month + 1
                        val monthStr = if (mon < 10) "0${mon}" else "${mon}"
                        dialogView.tv_tl.text= ("$dayStr-$mon-$year")
                        tanggal_lahir = "$year-$monthStr-$dayStr"
                    }
                })
            }
            dialogView.btn_add.onClick {
                if (dialogView.et_norm.text.isEmpty()|| tanggal_lahir==null){
                    dialogView.et_norm.error="No RM atau Tanggal Lahir Harus Diisi"
                }
                else{
                    progressDialog.setMessage("Application is loading, please wait")
                    progressDialog.show()
                    kartuPresenter.addkartu(dialogView.et_norm.text.toString(),tanggal_lahir.toString(),Utils.user_id.toString())
                }


            }
            dialogView.tv_luparm.onClick {

                val lupaRm = LupaRm()
                fragmentManager?.beginTransaction()
                    ?.replace(R.id.nav_host_fragment, lupaRm)
                    ?.addToBackStack(null)
                    ?.commit()




            }
            dialog!!.show()




        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment KartuFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            KartuFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onSuccessAdd(msg: String?) {
        progressDialog.dismiss()
       alert { 
           message=msg.toString()
           
       }.show()
        kartuPresenter.ekartu(Utils.user_id.toString())

    }

    override fun onSuccessDel(msg: String?) {
        alert { message=msg.toString() }
            .show()
        kartuPresenter.ekartu(Utils.user_id.toString())
    }

    override fun onFailedAdd(msg: String?) {
        progressDialog.dismiss()
        alert {
            message=msg.toString()

        }.show()
    }

    override fun onSuccessGetList(data: List<EkartuResultItem?>?) {
        progressDialog.dismiss()
        spinner_rm.adapter=EkartuAdapter(requireContext(), data as List<EkartuResultItem>)
        ekartuAdapter= EkartuAdapter(requireContext(), data)
        spinner_rm.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                no_rm=data[position].custUsrKode

                tv_nama.text=data[position].custUsrNama
                tv_rm.text=data[position].custUsrKode
                tv_tl.text=data[position].custUsrTglLahir

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }

    override fun onFailureAdd(msg: String?) {

    }
}