package com.itrsiam.rsiamuslimat.jadwal_dokter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.itrsiam.rsiamuslimat.R
import kotlinx.android.synthetic.main.item_jadwal.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick


class JadwalListAdapter(val data:List<JadwalResultItem>,private val click: onClickItem):RecyclerView.Adapter<JadwalListAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JadwalListAdapter.MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_jadwal,parent,false)
        return MyHolder(view)
    }

    override fun getItemCount() = data?.size ?: 0

    override fun onBindViewHolder(holder: JadwalListAdapter.MyHolder, position: Int) {
        holder.onBind(data?.get(position))
        holder.itemView.onClick {
            click.clicked(data?.get(position))
        }
    }
    class MyHolder(view: View):RecyclerView.ViewHolder(view) {
        @SuppressLint("SetTextI18n")
        fun onBind(get:JadwalResultItem?){
            itemView.nm_dokter.text=get?.dokterNama
            itemView.nm_poli.text=get?.poli_nama
            itemView.jadwal.text=get?.jadwalDokterHari+" ("+get?.jadwalDokterJamMulai+"-"+
                    get?.jadwalDokterJamSelesai+")"

            Glide.with(itemView.context)
                .load(get?.usr_foto)
                .error(R.drawable.ic_doctor)
                .into(itemView.profile_image)
        }

    }

    interface onClickItem{
        fun clicked (item: JadwalResultItem?)


    }

}