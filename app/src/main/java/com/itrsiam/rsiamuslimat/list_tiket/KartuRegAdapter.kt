package com.itrsiam.rsiamuslimat.list_tiket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itrsiam.rsiamuslimat.R
import kotlinx.android.synthetic.main.item_kartu.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class KartuRegAdapter(val data:List<TiketResults>?,private val click:onClickItem): RecyclerView.Adapter<KartuRegAdapter.RegHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):KartuRegAdapter.RegHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_kartu,parent,false)
        return KartuRegAdapter.RegHolder(view)
    }

    override fun onBindViewHolder(holder: RegHolder, position: Int) {
        holder.onBind(data?.get(position))
        holder.itemView.btn_qrcode.onClick {
            click.clicked(data?.get(position))
        }

    }

    override fun getItemCount() = data?.size ?:0

    class RegHolder (view: View): RecyclerView.ViewHolder(view) {


        fun onBind(get: TiketResults?) {
            itemView.tvnoantrian.text= get?.regBufferNoAntrian?.substring(3,3)
            itemView.tv_kunjungan.text=get?.regBufferTanggal+" "+get?.regBufferWaktu
            itemView.tv_dokter.text=get?.usrName+" ("+get?.poliNama+")"

        }

    }

    interface onClickItem {
        fun clicked(item: TiketResults?)


}
}

