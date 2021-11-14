package com.itrsiam.rsiamuslimat.riwayat_periksa

interface RiwayatPeriksaView {
    fun onSucccessPeriksa(
        data:List<ResultItem?>?
    )
    fun onFailedPeriksa(
        msg: String?
    )
}