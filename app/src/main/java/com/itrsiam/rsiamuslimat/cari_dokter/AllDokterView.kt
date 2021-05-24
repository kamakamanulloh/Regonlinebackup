package com.itrsiam.rsiamuslimat.cari_dokter

interface AllDokterView {
    fun onGetAllDokter(data: List<ResultItemDokter?>?)
    fun onCariDokter(data: List<ResultItemDokter?>?)
    fun onDetailDokter(data: List<ResultItemDetailDokter?>?)
    fun onFailedGetDokter(msg : String)


}