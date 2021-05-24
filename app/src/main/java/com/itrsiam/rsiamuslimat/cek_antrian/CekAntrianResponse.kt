package com.itrsiam.rsiamuslimat.cek_antrian

import com.google.gson.annotations.SerializedName

data class CekAntrianResponse(

	@field:SerializedName("poli_nama")
	val poliNama: String? = null,

	@field:SerializedName("reg_no_antrian")
	val regNoAntrian: String? = null,

	@field:SerializedName("dokter_nama")
	val dokterNama: String? = null,

	@field:SerializedName("value")
	val value: Int? = null
)
