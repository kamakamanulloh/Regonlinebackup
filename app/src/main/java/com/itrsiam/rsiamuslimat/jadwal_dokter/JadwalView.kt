package com.itrsiam.rsiamuslimat.jadwal_dokter

interface JadwalView {
    fun onGetJadwal(data: List<JadwalResultItem?>?)
    fun onFailedGetJadwal(msg : String)

}