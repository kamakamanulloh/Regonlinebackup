package com.itrsiam.rsiamuslimat.pasien.bpjs

interface CekKepesertaanView {
    fun onSuccessCek (
        msg: String?,
        nama: String?,
        status: String?,
        asal: String?,
        jenis: String?


    )
    fun onFailedCek (msg : String?)
}