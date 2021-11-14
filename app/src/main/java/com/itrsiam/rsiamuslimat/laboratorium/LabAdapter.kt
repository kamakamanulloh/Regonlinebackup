package com.itrsiam.rsiamuslimat.laboratorium

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itrsiam.rsiamuslimat.R
import com.itrsiam.rsiamuslimat.laboratorium.LabAdapter
import kotlinx.android.synthetic.main.item_radiologi.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class LabAdapter(val data:List<ResultItem>?,private val click:onClickItem):
    RecyclerView.Adapter<LabAdapter.ViewHolder>() {

    class ViewHolder (view: View):RecyclerView.ViewHolder(view) {
        fun onBind(get: ResultItem){
            itemView.tv_tanggal.text=get?.regTanggal
            itemView.tv_pemeriksaan.text=get?.custUsrNama
            itemView.tvitem.text="Nama Pasien"
        }

    }
    interface onClickItem{
        fun clicked (item:ResultItem?)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabAdapter.ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_radiologi,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: LabAdapter.ViewHolder, position: Int) {
        holder.onBind(data?.get(position)!!)
        holder.itemView.btn_detail.onClick {

            click.clicked(data[position])


        }
    }

    override fun getItemCount()=data?.size?:0
}