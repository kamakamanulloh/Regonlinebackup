package com.itrsiam.rsiamuslimat.pengingat_kontrol

import com.google.gson.annotations.SerializedName

data class PengingatKontrolResponse(

	@field:SerializedName("result")
	val result: List<ResultItem?>? = null,

	@field:SerializedName("value")
	val value: Int? = null
)

data class ResultItem(

	@field:SerializedName("dokter")
	val dokter: String? = null,

	@field:SerializedName("poli_nama")
	val poliNama: String? = null,

	@field:SerializedName("tgl_kontrol")
	val tglKontrol: String? = null,

	@field:SerializedName("reg_id")
	val regId: String? = null
)
