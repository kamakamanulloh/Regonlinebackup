package com.itrsiam.rsiamuslimat.list_tiket

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
import org.jetbrains.anko.support.v4.startActivity

class TiketFragment : Fragment(),TiketView {

    companion object {
        fun newInstance() = TiketFragment()
    }

    private lateinit var viewModel: TiketViewModel
    private lateinit var presenter: TiketPresenter

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
        // TODO: Use the ViewModel
    }


    override fun onGetTiket(data: List<TiketResults?>?) {
        progress_bar.visibility=(View.GONE)

        rv.adapter=TiketAdapter(data as List<TiketResults>,object :TiketAdapter.onClickItem{
            override fun clicked(item: TiketResults?) {
                startActivity<TicketViewActivity>("dataItem" to item)


            }
        })

    }

    override fun onFailedGetTiket(msg: String) {
        progress_bar.visibility=(View.GONE)
        context?.alert {


            message=msg
        }?.show()
    }

    override fun onGet(msg: String) {

    }

    override fun onFailed(msg: String) {

    }

}
