package com.itrsiam.rsiamuslimat.lupa_rm

interface LupaRmView {
    fun onSuccessLogin (
        msg: String?,
        pasien_id: String?,
        pasien_rm: String?,
        pasien_nama: String?,
        pasien_tl: String?,

        pasien_alamat: String?,
        cust_usr_no_identitas: String?,
        cust_usr_no_jaminan: String?
    )
    fun onFailedLogin (msg : String?)



    fun onFailure(msg: String?)
}