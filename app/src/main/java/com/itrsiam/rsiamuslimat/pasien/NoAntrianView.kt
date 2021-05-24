package com.itrsiam.rsiamuslimat.pasien

interface NoAntrianView {
    fun onSucces(
        namaPx: String?,
        regBufferNoAntrian: String?,
        pasienNama: String?,
        poliNama: String?,
        loginCustPhoneNumber: String?,

        jenisNama: String?,
        regBufferWaktu: String?,
        perusahaanNama: String?,
        regBufferId: String?,
        regBufferTanggal: String?,
        usrName: String?,
        noRm: String?,
        regBufferNobpjs: String?



    )

    fun onFailed (msg : String?)



    fun onFailure(msg: String?)
}