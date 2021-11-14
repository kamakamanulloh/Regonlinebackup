package com.itrsiam.rsiamuslimat.diagnosa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itrsiam.rsiamuslimat.R
import kotlinx.android.synthetic.main.item_diagnosa.view.*
import kotlinx.android.synthetic.main.itempoli.view.*


class DiagnosaAdapter(val data:List<ResultItem>?):RecyclerView.Adapter<DiagnosaAdapter.ViewHolder>() {
    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        fun onBind(get:ResultItem){
            itemView.tv_diagnosa.text=get.icdDeskripsi+" ("+get.icdNomor+")"
            itemView.tv_dokter.text=get.dokter
            itemView.tv_poli.text=get.poliNama
            itemView.tv_tanggal.text=get.regTanggal
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiagnosaAdapter.ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_diagnosa,parent,false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: DiagnosaAdapter.ViewHolder, position: Int) {
        holder.onBind(data?.get(position)!!)
    }

    override fun getItemCount()=data?.size?:0
}