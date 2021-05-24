package com.itrsiam.rsiamuslimat.pasien.bpjs

interface TipeJKNView {
    fun onSuccessGetjkn(data: List<jknResultItem?>?)
    fun onFailedGetjkn(msg : String)
}