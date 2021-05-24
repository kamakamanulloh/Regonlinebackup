package com.itrsiam.rsiamuslimat.poli

interface ListPoliView {
    fun onSuccessGet(data: List<PoliResultItem?>?)
    fun onFailedGet(msg : String)

}