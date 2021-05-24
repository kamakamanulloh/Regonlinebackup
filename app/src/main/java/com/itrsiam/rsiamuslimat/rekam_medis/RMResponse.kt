package com.itrsiam.rsiamuslimat.rekam_medis

import com.google.gson.annotations.SerializedName

data class RMResponse(

	@field:SerializedName("result")
	val result: List<RMResultItem?>? = null,

	@field:SerializedName("value")
	val value: Int? = null
)

data class RMResultItem(

	@field:SerializedName("reg_tanggal")
	val regTanggal: String? = null,

	@field:SerializedName("pasien_rm")
	val pasienRm: String? = null,

	@field:SerializedName("poli_nama")
	val poliNama: String? = null,

	@field:SerializedName("cust_usr_id")
	val custUsrId: String? = null,

	@field:SerializedName("jenis_nama")
	val jenisNama: String? = null,

	@field:SerializedName("cust_usr_nama")
	val custUsrNama: String? = null
)
