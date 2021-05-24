package com.itrsiam.rsiamuslimat.jadwal_dokter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.itrsiam.rsiamuslimat.R

class JadwalAdapter(context: Context, var listjadwal: List<JadwalResultItem>): BaseAdapter() {

    val mInflater: LayoutInflater = LayoutInflater.from(context)
    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val vh: ItemRowHolder
        val nm_dokter : String
        val jamselesai:String
        val jammulai:String
        val jadwalhari:String

        if (convertView == null) {
            view = mInflater.inflate(R.layout.listjadwal, parent, false)
            vh = ItemRowHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemRowHolder
        }
        if (listjadwal[position].dokterNama.equals("Heru Setiawan, Sp.A, dr") &&  listjadwal[position].jadwalDokterJamMulai.equals("07:00:00") &&  listjadwal[position].jadwalDokterJamSelesai.equals("12:00:00"))
        {
            nm_dokter=" "
            jamselesai=" "
            jammulai=" "
            jadwalhari=" "

        }
        else{
            nm_dokter=listjadwal[position].dokterNama.toString()
            jamselesai= listjadwal[position].jadwalDokterJamSelesai.toString()
            jammulai=listjadwal[position].jadwalDokterJamMulai.toString()
            jadwalhari=listjadwal[position].jadwalDokterHari.toString()
        }



        vh.label.text = nm_dokter
        vh.tvjadwal.text= "$jadwalhari ($jammulai - $jamselesai )"
        return view
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return listjadwal.size
    }

    private class ItemRowHolder(row: View?) {


        val label: TextView
        val tvjadwal: TextView

        init {
            this.label = row?.findViewById(R.id.tvdokter) as TextView
            this.tvjadwal = row?.findViewById(R.id.tvjadwal) as TextView
        }
    }

}