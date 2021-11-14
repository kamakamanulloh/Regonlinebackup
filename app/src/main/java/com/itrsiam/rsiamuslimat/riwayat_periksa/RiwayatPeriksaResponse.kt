package com.itrsiam.rsiamuslimat.riwayat_periksa

import com.google.gson.annotations.SerializedName

data class RiwayatPeriksaResponse(

	@field:SerializedName("result")
	val result: List<ResultItem?>? = null,

	@field:SerializedName("value")
	val value: Int? = null
)

data class ResultItem(

	@field:SerializedName("reg_tanggal")
	val regTanggal: String? = null,

	@field:SerializedName("dokter")
	val dokter: String? = null,

	@field:SerializedName("poli_nama")
	val poliNama: String? = null,

	@field:SerializedName("reg_id")
	val regId: String? = null
)
