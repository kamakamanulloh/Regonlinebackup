package com.itrsiam.rsiamuslimat.info

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itrsiam.rsiamuslimat.R
import kotlinx.android.synthetic.main.item_info.view.*

class InfoAdapter(var data:List<ResultItemInfo>):RecyclerView.Adapter<InfoAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoAdapter.ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_info,parent,false)
        return ViewHolder(view)
    }

    class ViewHolder (view: View):RecyclerView.ViewHolder(view){
        fun onBind(get:ResultItemInfo){
            itemView.tv_isi.text=get.isi


        }

    }

    override fun getItemCount()=data.size

    override fun onBindViewHolder(holder: InfoAdapter.ViewHolder, position: Int) {
        holder.onBind(data[position])
    }
}