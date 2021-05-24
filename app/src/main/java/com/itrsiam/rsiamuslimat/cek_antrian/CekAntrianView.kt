package com.itrsiam.rsiamuslimat.cek_antrian

interface CekAntrianView {
    fun onSuccessAntrian(

        reg_no_antrian: String?,
        poli_nama: String?,
        dokter_nama: String?

    )
    fun onFailedAntri(
        msg:String
    )
}