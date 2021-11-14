package com.itrsiam.rsiamuslimat.diagnosa

import com.google.gson.annotations.SerializedName

data class DiagnosaResponse(

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

	@field:SerializedName("icd_deskripsi")
	val icdDeskripsi: String? = null,

	@field:SerializedName("icd_nomor")
	val icdNomor: String? = null,

	@field:SerializedName("icd_nama")
	val icdNama: String? = null
)
