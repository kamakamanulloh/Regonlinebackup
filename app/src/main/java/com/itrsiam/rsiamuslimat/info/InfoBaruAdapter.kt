package com.itrsiam.rsiamuslimat.info

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itrsiam.rsiamuslimat.R
import kotlinx.android.synthetic.main.item_info.view.*

class InfoBaruAdapter(var data:List<ResultItemInfo>): RecyclerView.Adapter<InfoBaruAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoBaruAdapter.ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_info,parent,false)
        return ViewHolder(view)
    }

    class ViewHolder (view: View): RecyclerView.ViewHolder(view){
        fun onBind(get:ResultItemInfo){
            itemView.tv_isi.text=get.isi


        }

    }

    override fun getItemCount()=data.size

    override fun onBindViewHolder(holder: InfoBaruAdapter.ViewHolder, position: Int) {
        holder.onBind(data[position])
    }
}