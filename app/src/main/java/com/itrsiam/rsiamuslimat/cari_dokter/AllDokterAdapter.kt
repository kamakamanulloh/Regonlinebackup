package com.itrsiam.rsiamuslimat.cari_dokter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.itrsiam.rsiamuslimat.R
import kotlinx.android.synthetic.main.item_dokter.view.*

import org.jetbrains.anko.sdk27.coroutines.onClick

class AllDokterAdapter(var data:List<ResultItemDokter>,private val click:onClickItem):RecyclerView.Adapter<AllDokterAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_dokter,parent,false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View):RecyclerView.ViewHolder(view)  {
        fun onBind(get: ResultItemDokter) {
            itemView.nm_dokter.text=get?.dokterNama

            Glide.with(itemView.context)
                .load(get?.usrFoto)
                .error(R.drawable.ic_doctor)
                .into(itemView.profile_image)

        }


    }

    override fun getItemCount()=data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(data[position])
        holder.itemView.onClick {
            click.clicked(data[position])
        }

    }
    interface onClickItem{
        fun clicked(item: ResultItemDokter)
    }
}