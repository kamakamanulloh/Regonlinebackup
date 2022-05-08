package com.itrsiam.rsiamuslimat.pasien

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itrsiam.rsiamuslimat.R
import com.itrsiam.rsiamuslimat.kartu.EkartuResultItem
import kotlinx.android.synthetic.main.item_pasien.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class EkartuPasienAdapter(val data:List<EkartuResultItem>?,private val click:onClickItem ):
    RecyclerView.Adapter<EkartuPasienAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("SetTextI18n")
        fun onbind(get: EkartuResultItem?) {
            itemView.tv_norm.text=get?.custUsrKode+" - "+get?.custUsrNama
            itemView.tv_tgl_lahir.text=get?.custUsrTglLahir

        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.item_pasien,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.onbind(data?.get(position))

        holder.itemView.btn_pilih.onClick {
            click.clicked(data?.get(position))
        }

    }

    override fun getItemCount() = data?.size ?: 0

    interface onClickItem {
        fun clicked(item: EkartuResultItem?) {

        }

    }

}

