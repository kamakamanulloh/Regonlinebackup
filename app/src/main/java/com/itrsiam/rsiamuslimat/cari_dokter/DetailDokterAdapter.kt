package com.itrsiam.rsiamuslimat.cari_dokter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itrsiam.rsiamuslimat.R

import kotlinx.android.synthetic.main.item_waktu_praktek.view.*

class DetailDokterAdapter(var data:List<ResultItemDetailDokter>):RecyclerView.Adapter<DetailDokterAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_waktu_praktek,parent,false)
        return ViewHolder(view)
    }

    override fun  getItemCount()=data.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(data[position])

    }
    class ViewHolder(view: View):RecyclerView.ViewHolder(view)  {
        @SuppressLint("SetTextI18n")
        fun onBind(get: ResultItemDetailDokter) {
            itemView.tv_jadwal.text=get.jadwalDokterHari
            itemView.tv_jam.text=get.jadwalDokterJamMulai+" "+get.jadwalDokterJamSelesai


        }


    }
}