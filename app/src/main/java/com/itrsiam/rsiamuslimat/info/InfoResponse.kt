package com.itrsiam.rsiamuslimat.info

import com.google.gson.annotations.SerializedName

data class InfoResponse(

	@field:SerializedName("result")
	val result: List<ResultItemInfo?>? = null,

	@field:SerializedName("value")
	val value: Int? = null
)

data class ResultItemInfo(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("tanggal")
	val tanggal: String? = null,

	@field:SerializedName("isi")
	val isi: String? = null
)
