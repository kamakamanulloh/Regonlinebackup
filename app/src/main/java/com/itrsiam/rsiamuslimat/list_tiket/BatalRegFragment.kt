package com.itrsiam.rsiamuslimat.list_tiket

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.itrsiam.rsiamuslimat.R
import com.itrsiam.rsiamuslimat.api.Utils
import kotlinx.android.synthetic.main.fragment_batal_reg.*

import org.jetbrains.anko.support.v4.alert

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BatalRegFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("UNCHECKED_CAST")
class BatalRegFragment : Fragment(),TiketView {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var presenter: TiketPresenter
    private lateinit var progressDialog : ProgressDialog
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
        val v= inflater.inflate(R.layout.fragment_batal_reg, container, false)
        progressDialog= ProgressDialog(requireContext())
        progressDialog.setMessage("Proses Ambil Data Mohon Tunggu")
        progressDialog.show()

        return v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BatalRegFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BatalRegFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter= TiketPresenter(this)
        presenter.getBatalTiket(Utils.user_id!!)
    }

    override fun onGetTiket(data: List<TiketResults?>?) {
        progressDialog.dismiss()

        rv_batal.adapter=BatalAdapter(data as List<TiketResults>,object :BatalAdapter.onClickItem{
            override fun clicked(item: TiketResults?) {
                val builder = AlertDialog.Builder(requireContext())
                builder.setMessage(R.string.dialog)
                    .setPositiveButton(R.string.ya,
                        DialogInterface.OnClickListener { dialog, id ->
                            // FIRE ZE MISSILES!
                            presenter.batal_reg(item?.regBufferId.toString())

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
        })
    }


    override fun onFailedGetTiket(msg: String) {
        progressDialog.dismiss()
    }

    override fun onGet(msg: String) {
        alert {
            message="Pembatalan Berhasil"
        }.show()
        presenter.getTiket(Utils.user_id!!)


    }

    override fun onFailed(msg: String) {
        alert {
            message="Gagal"
        }.show()

    }
}