package com.itrsiam.rsiamuslimat.laboratorium

import com.google.gson.annotations.SerializedName

data class LabResponse(

	@field:SerializedName("result")
	val result: List<ResultItem?>? = null,

	@field:SerializedName("value")
	val value: Int? = null
)

data class ResultItem(

	@field:SerializedName("pemeriksaan_id")
	val pemeriksaanId: String? = null,

	@field:SerializedName("reg_tanggal")
	val regTanggal: String? = null,

	@field:SerializedName("cust_usr_nama")
	val custUsrNama: String? = null,
	@field:SerializedName("reg_id")
	val regId: String? = null,

	@field:SerializedName("id_pembayaran")
	val idPembayaran: String? = null
)
