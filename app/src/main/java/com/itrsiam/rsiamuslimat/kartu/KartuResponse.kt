package com.itrsiam.rsiamuslimat.kartu

import com.google.gson.annotations.SerializedName

data class KartuResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("value")
	val value: Int? = null
)
