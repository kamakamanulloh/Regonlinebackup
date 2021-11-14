package com.itrsiam.rsiamuslimat.riwayat_periksa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itrsiam.rsiamuslimat.R
import kotlinx.android.synthetic.main.item_kontrol.view.*

class RiwayatPeriksaAdapter(val data:List<ResultItem>?):
    RecyclerView.Adapter<RiwayatPeriksaAdapter.ViewHolder>() {

    class ViewHolder (view: View):RecyclerView.ViewHolder(view) {
        fun onBind(get: ResultItem){
            itemView.tv_poli.text=get.poliNama+" ("+get.dokter+")"
            itemView.tv_tglkontrol.text=get.regTanggal
            itemView.tvitem2.text="Tanggal Periksa"
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RiwayatPeriksaAdapter.ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_kontrol,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RiwayatPeriksaAdapter.ViewHolder, position: Int) {
        holder.onBind(data?.get(position)!!)

    }

    override fun getItemCount()=data?.size?:0
}