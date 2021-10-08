package com.itrsiam.rsiamuslimat.radiologi

import com.google.gson.annotations.SerializedName

data class RadilogiResponse(

	@field:SerializedName("result")
	val result: List<ResultItem?>? = null,

	@field:SerializedName("value")
	val value: Int? = null
)

data class ResultItem(

	@field:SerializedName("reg_tanggal")
	val regTanggal: String? = null,

	@field:SerializedName("biaya_nama")
	val biayaNama: String? = null,

	@field:SerializedName("pemeriksaan_pasien_nama")
	val pemeriksaanPasienNama: String? = null,

	@field:SerializedName("resume_id")
	val resumeId: String? = null
)
