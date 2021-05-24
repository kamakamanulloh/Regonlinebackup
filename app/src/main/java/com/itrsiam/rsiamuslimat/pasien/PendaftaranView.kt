package com.itrsiam.rsiamuslimat.pasien

interface PendaftaranView {
    fun onSuccessPendaftaran(msg:String?,id_buffer:String?,noAntrian:String?)
    fun onFailedRPendaftaran(msg: String?)
    fun onFullRPendaftaran(msg: String?)
}