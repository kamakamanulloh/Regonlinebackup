package com.itrsiam.rsiamuslimat.pasien.bpjs

import com.google.gson.annotations.SerializedName

data class TipejknResponse(

	@field:SerializedName("result")
	val result: List<jknResultItem?>? = null,

	@field:SerializedName("value")
	val value: Int? = null
)

data class jknResultItem(

	@field:SerializedName("jkn_nama")
	val jknNama: String? = null,

	@field:SerializedName("jkn_id")
	val jknId: String? = null
)
