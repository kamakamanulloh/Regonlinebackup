package com.itrsiam.rsiamuslimat.rekam_medis

interface RMView {
    fun onGetRM(data: List<RMResultItem?>?)
    fun onFailedGetRM(msg : String)

}