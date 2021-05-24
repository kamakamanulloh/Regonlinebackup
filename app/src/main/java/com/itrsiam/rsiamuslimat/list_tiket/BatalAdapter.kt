package com.itrsiam.rsiamuslimat.list_tiket

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itrsiam.rsiamuslimat.R

import kotlinx.android.synthetic.main.item_tiket_batal.view.*
import kotlinx.android.synthetic.main.item_tiket_batal.view.nama_rm
import kotlinx.android.synthetic.main.item_tiket_batal.view.status
import kotlinx.android.synthetic.main.item_tiket_batal.view.titleantrian
import kotlinx.android.synthetic.main.item_tiket_batal.view.tv_dokter
import kotlinx.android.synthetic.main.item_tiket_batal.view.tv_nm_klinik
import kotlinx.android.synthetic.main.item_tiket_batal.view.tv_rm
import org.jetbrains.anko.sdk27.coroutines.onClick

class BatalAdapter(val data:List<TiketResults>?, private val click: onClickItem): RecyclerView.Adapter<BatalAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BatalAdapter.MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tiket_batal, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount() = data?.size ?: 0

    override fun onBindViewHolder(holder: BatalAdapter.MyHolder, position: Int) {
        holder.onBind(data?.get(position))
        holder.itemView.onClick {
            click.clicked(data?.get(position))
        }
        holder.itemView.batal.onClick {
            click.clicked(data?.get(position))
        }
    }

    class MyHolder(view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("SetTextI18n")
        fun onBind(get: TiketResults?) {
            itemView.titleantrian.text = get?.regBufferTanggal + "( " + get?.regBufferWaktu + ")"

            itemView.tv_nm_klinik.text = get?.poliNama
            itemView.tv_dokter.text = get?.usrName
            if (get?.reg_buffer_batal.equals("n")){

            }
            else if (get?.reg_buffer_batal.equals("y")){
                itemView.status.text="Batal Registrasi"
            }

            if (get?.jadwalDokterStatus == "LIBUR"){
                itemView.statusJadwal.text="Dokter Libur"

            }
            else if (get?.jadwalDokterStatus == "PRAKTEK"){
                itemView.statusJadwal.text=" "

            }
           itemView.nb.text=get?.poliNama

            itemView.tv_rm.text = get?.custUsrKode
            itemView.nama_rm.text = get?.custUsrNama


        }

    }

    interface onClickItem {
        fun clicked(item: TiketResults?)


    }
}