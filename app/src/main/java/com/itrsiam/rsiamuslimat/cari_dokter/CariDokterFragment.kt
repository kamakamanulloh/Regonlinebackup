package com.itrsiam.rsiamuslimat.cari_dokter


import android.app.AlertDialog
import android.app.ProgressDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.itrsiam.rsiamuslimat.R
import com.itrsiam.rsiamuslimat.pasien.asuransi.AsuransiFragment
import com.itrsiam.rsiamuslimat.pasien.bpjs.BpjsFragment
import com.itrsiam.rsiamuslimat.pasien.umum.PasienUmumFragment
import kotlinx.android.synthetic.main.bottom_sheet_dialog.view.*
import kotlinx.android.synthetic.main.fragment_cari_dokter.*
import kotlinx.android.synthetic.main.item_detail_dokter.view.*

import org.jetbrains.anko.alert


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CariDokterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("UNCHECKED_CAST")
class CariDokterFragment : Fragment(), AllDokterView {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var allDokterPresenter: AllDokterPresenter
    private lateinit var progressDialog : ProgressDialog
    var dialog: AlertDialog.Builder? = null
    var inflater: LayoutInflater? = null

    private var nm_dokter:String?=null
    private var foto:String?=null


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
        return inflater.inflate(R.layout.fragment_cari_dokter, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CariDokterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CariDokterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        allDokterPresenter= AllDokterPresenter(this)
        allDokterPresenter.getAllDokter()
        progressDialog = ProgressDialog(requireContext())
        et_CariDokter.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {


            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.length!! > 0){
                    allDokterPresenter.getCariDokter(p0.toString())
                }
                else
                {
                    allDokterPresenter.getAllDokter()
                }




            }

        })
    }

    override fun onGetAllDokter(data: List<ResultItemDokter?>?) {
        progressDialog = ProgressDialog(requireContext())
        progressDialog.dismiss()

        rv_dokter.adapter=AllDokterAdapter(data as List<ResultItemDokter>,object :AllDokterAdapter.onClickItem{

            override fun clicked(item: ResultItemDokter) {
//                alert {
//                    message=item.usrId.toString()
//                }.show()

                allDokterPresenter.getDetailDokter(item.usrId.toString())
                nm_dokter=item.dokterNama.toString()
                foto=item.usrFoto.toString()
            }

        })



    }

    override fun onCariDokter(data: List<ResultItemDokter?>?) {
        progressDialog = ProgressDialog(requireContext())
        progressDialog.dismiss()

        rv_dokter.adapter=AllDokterAdapter(data as List<ResultItemDokter>,object :AllDokterAdapter.onClickItem{

            override fun clicked(item: ResultItemDokter) {

//                alert {
//                    message=item.usrId.toString()
//                }.show()

                allDokterPresenter.getDetailDokter(item.usrId.toString())
            }





        })

    }



    override fun onDetailDokter(data: List<ResultItemDetailDokter?>?) {

        dialog = AlertDialog.Builder(requireContext())
        inflater = layoutInflater
      val  dialogView = inflater!!.inflate(R.layout.item_detail_dokter, null)
//        val mDialogView = LayoutInflater.from(requireContext()).inflate(R.layout.item_detail_dokter, null)
        dialog!!.setView(dialogView)
        dialog!!.setCancelable(true)
        dialog!!.setTitle("Jadwal Dokter $nm_dokter")


        Glide.with(requireContext())
            .load(foto)
            .error(R.drawable.ic_doctor)
            .into(dialogView.profile_image)
        dialogView.rv_jadwal.adapter=DetailDokterAdapter(data as List<ResultItemDetailDokter>)




        dialog!!.show()



    }

    private fun dialog() {

        val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)
        val dialog = context?.let { it1 -> BottomSheetDialog(it1) }
        dialog?.setContentView(view)
        view.umum.setOnClickListener {
            var pickpowerfrag = PasienUmumFragment()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment, pickpowerfrag)
                ?.addToBackStack(null)
                ?.commit()
            dialog?.dismiss()

        }
        view.bpjs.setOnClickListener {
            var bpjs = BpjsFragment()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment, bpjs)
                ?.addToBackStack(null)
                ?.commit()
            dialog?.dismiss()

        }
        view.asuransi.setOnClickListener {

            var asuransi = AsuransiFragment()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment, asuransi)
                ?.addToBackStack(null)
                ?.commit()
            dialog?.dismiss()
        }


        dialog?.show()


    }

    override fun onFailedGetDokter(msg: String) {
        progressDialog = ProgressDialog(requireContext())
        progressDialog.dismiss()

        requireContext().alert {
            message=msg
        }.show()

    }
}