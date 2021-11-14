package com.itrsiam.rsiamuslimat.pengingat_kontrol

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itrsiam.rsiamuslimat.R
import kotlinx.android.synthetic.main.item_kontrol.view.*


class PengingatKontrolAdapter(val data:List<ResultItem>?):
    RecyclerView.Adapter<PengingatKontrolAdapter.ViewHolder>() {
    class ViewHolder (view: View):RecyclerView.ViewHolder(view) {
        fun onBind(get:ResultItem){
            itemView.tv_poli.text=get.poliNama+" ("+get.dokter+")"
            itemView.tv_tglkontrol.text=get.tglKontrol
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PengingatKontrolAdapter.ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_kontrol,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PengingatKontrolAdapter.ViewHolder, position: Int) {
        holder.onBind(data?.get(position)!!)

    }

    override fun getItemCount()=data?.size?:0
}