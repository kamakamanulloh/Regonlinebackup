package com.itrsiam.rsiamuslimat.pasien.asuransi

import com.google.gson.annotations.SerializedName

data class AsuransiResponse(

	@field:SerializedName("result")
	val result: List<AuransiResultItem?>? = null,

	@field:SerializedName("value")
	val value: Int? = null
)

data class AuransiResultItem(

	@field:SerializedName("perusahaan_id")
	val perusahaanId: String? = null,

	@field:SerializedName("perusahaan_nama")
	val perusahaanNama: String? = null
)
