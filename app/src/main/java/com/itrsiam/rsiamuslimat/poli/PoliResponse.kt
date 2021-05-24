package com.itrsiam.rsiamuslimat.poli

import com.google.gson.annotations.SerializedName

data class PoliResponse(

	@field:SerializedName("result")
	val result: List<PoliResultItem?>? = null,

	@field:SerializedName("value")
	val value: Int? = null
)

data class PoliResultItem(

	@field:SerializedName("poli_nama")
	val poliNama: String? = null,

	@field:SerializedName("poli_id")
	val poliId: String? = null
)
