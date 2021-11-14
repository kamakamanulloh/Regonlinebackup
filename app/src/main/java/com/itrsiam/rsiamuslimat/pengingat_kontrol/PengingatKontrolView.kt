package com.itrsiam.rsiamuslimat.pengingat_kontrol

interface PengingatKontrolView {
    fun onSuccessKontrol(
        data:List<ResultItem?>?
    )
    fun onFailedKontrol(
        msg:String?
    )
}