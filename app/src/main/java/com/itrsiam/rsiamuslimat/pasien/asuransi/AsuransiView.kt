package com.itrsiam.rsiamuslimat.pasien.asuransi

interface AsuransiView {
    fun onSuccessGetAsuransi(data: List<AuransiResultItem?>?)
    fun onFailedGetAsuransi(msg : String)
}