package com.itrsiam.rsiamuslimat.poli

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.itrsiam.rsiamuslimat.R

class PoliAdapter(context: Context,var listItemsTxt: List<PoliResultItem>):BaseAdapter() {

    val mInflater: LayoutInflater = LayoutInflater.from(context)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val vh: ItemRowHolder
        if (convertView == null) {
            view = mInflater.inflate(R.layout.itempoli, parent, false)
            vh = ItemRowHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemRowHolder
        }
        val params = view.layoutParams
        params.height = 60
        view.layoutParams = params

        vh.label.text = listItemsTxt.get(position).poliNama
        return view
    }

        override fun getItem(position: Int): Any? {
            return null
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getCount(): Int {
          return listItemsTxt.size
        }

    private class ItemRowHolder(row: View?) {


        val label: TextView

        init {
            this.label = row?.findViewById(R.id.textView) as TextView
        }
    }



}






