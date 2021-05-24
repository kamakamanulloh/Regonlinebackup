package com.itrsiam.rsiamuslimat.rekam_medis

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itrsiam.rsiamuslimat.R
import kotlinx.android.synthetic.main.item_jadwal.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class RMAdapter  (val data:List<RMResultItem>?, private val click: onClickItem): RecyclerView.Adapter<RMAdapter.MyHolder>(){

    interface onClickItem{
        fun clicked (item: RMResultItem?)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RMAdapter.MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_jadwal,parent,false)
        return MyHolder(view)
    }

    override fun getItemCount() = data?.size ?: 0


    override fun onBindViewHolder(holder: RMAdapter.MyHolder, position: Int) {
        holder.onBind(data?.get(position))
        holder.itemView.onClick {
            click.clicked(data?.get(position))
        }
    }

    class MyHolder(view: View):RecyclerView.ViewHolder(view) {
        @SuppressLint("SetTextI18n")
        fun onBind(get:RMResultItem?){
            itemView.nm_dokter.text=get?.poliNama
            itemView.nm_poli.text=get?.jenisNama
            itemView.jadwal.text=get?.regTanggal
        }

    }
}