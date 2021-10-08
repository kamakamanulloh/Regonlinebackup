package com.itrsiam.rsiamuslimat.radiologi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itrsiam.rsiamuslimat.R
import kotlinx.android.synthetic.main.item_radiologi.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import retrofit2.http.GET

class RadiologiAdapter(val data:List<ResultItem>?):
    RecyclerView.Adapter<RadiologiAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadiologiAdapter.ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_radiologi,parent,false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: RadiologiAdapter.ViewHolder, position: Int) {
       holder.itemView.btn_detail.onClick {

       }
    }

    override fun getItemCount()=data?.size?:0


    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        fun onBind(get: ResultItem){
            itemView.tv_tanggal.text=get?.regTanggal
            itemView.tv_pemeriksaan.text=get?.biayaNama
        }
    }
}