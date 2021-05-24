package com.itrsiam.rsiamuslimat.lupa_rm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.itrsiam.rsiamuslimat.R
import kotlinx.android.synthetic.main.fragment_lupa_rm.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.alert

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LupaRm.newInstance] factory method to
 * create an instance of this fragment.
 */
class LupaRm : Fragment(),LupaRmView {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var lupaRmPresenter: LupaRmPresenter

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
        return inflater.inflate(R.layout.fragment_lupa_rm, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LupaRm.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LupaRm().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lupaRmPresenter= LupaRmPresenter(this)

        btn_proses.onClick {
            if (spn_jenisId.selectedItem == "------" || txt_id.toString().isEmpty()){
                alert {
                    message="Mohon untuk melengkapi form"
                }.show()
            }
            else{

                lupaRmPresenter.pulihkan_rm(txt_id.text.toString(),spn_jenisId.selectedItem.toString())
            }

        }

    }


    override fun onSuccessLogin(
        msg: String?,
        pasien_id: String?,
        pasien_rm: String?,
        pasien_nama: String?,
        pasien_tl: String?,
        pasien_alamat: String?,
        cust_usr_no_identitas: String?,
        cust_usr_no_jaminan: String?
    ) {


        txt_hasil_id.setText(pasien_rm)
        tv_notif.text = "Data Ditemukan Silahkan Copy Paste"


    }

    override fun onFailedLogin(msg: String?) {


        alert {
            message="Data Tidak Ditemukan"
        }.show()
    }

    override fun onFailure(msg: String?) {


        alert {
            message="Gagal"
        }.show()
    }
}