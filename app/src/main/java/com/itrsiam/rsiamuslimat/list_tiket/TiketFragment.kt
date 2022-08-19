package com.itrsiam.rsiamuslimat.list_tiket

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.itrsiam.rsiamuslimat.R
import com.itrsiam.rsiamuslimat.api.Utils
import com.itrsiam.rsiamuslimat.pasien.TicketViewActivity
import kotlinx.android.synthetic.main.tiket_fragment.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.startActivity

class TiketFragment : Fragment(),TiketView {

    companion object {
        fun newInstance() = TiketFragment()
    }

    private lateinit var viewModel: TiketViewModel
    private lateinit var presenter: TiketPresenter
    private lateinit var progressDialog : ProgressDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tiket_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TiketViewModel::class.java)
        presenter= TiketPresenter(this)
        presenter.getTiket(Utils.user_id!!)
        progressDialog= ProgressDialog(requireContext())
        progressDialog.setMessage("Proses Ambil Data Mohon Tunggu")
        progressDialog.show()

        // TODO: Use the ViewModel
    }


    override fun onGetTiket(data: List<TiketResults?>?) {
       progressDialog.dismiss()

        rv.adapter=TiketAdapter(data as List<TiketResults>,object :TiketAdapter.onClickItem{
            override fun clicked(item: TiketResults?) {
                startActivity<TicketViewActivity>("dataItem" to item)


            }

            override fun clickcancel(item: TiketResults?) {
                val builder = AlertDialog.Builder(requireContext())
                builder.setMessage(R.string.dialog)
                    .setPositiveButton(R.string.ya,
                        DialogInterface.OnClickListener { dialog, id ->
                            // FIRE ZE MISSILES!
                            progressDialog.setMessage("Mohon Tungu Sebentar")
                            progressDialog.show()
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
        context?.alert {


            message=msg
        }?.show()
    }

    override fun onGet(msg: String) {
        alert {
            message="Pembatalan Berhasil"
        }.show()
        progressDialog.dismiss()
        presenter.getTiket(Utils.user_id!!)
    }

    override fun onFailed(msg: String) {
        alert {
            message=msg.toString()
        }.show()
        presenter.getTiket(Utils.user_id!!)
    }
    override fun onDestroy() {
        super.onDestroy()
    }
}
