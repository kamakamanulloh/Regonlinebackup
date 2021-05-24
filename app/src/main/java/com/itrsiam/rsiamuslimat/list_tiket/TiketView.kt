package com.itrsiam.rsiamuslimat.list_tiket

interface TiketView {
    fun onGetTiket(data: List<TiketResults?>?)
    fun onFailedGetTiket(msg : String)

    fun onGet(msg : String)
    fun onFailed(msg : String)

}