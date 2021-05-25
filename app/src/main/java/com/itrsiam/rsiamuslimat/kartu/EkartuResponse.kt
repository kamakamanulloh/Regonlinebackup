package com.itrsiam.rsiamuslimat.kartu

import com.google.gson.annotations.SerializedName

data class EkartuResponse(

	@field:SerializedName("result")
	val result: List<EkartuResultItem?>? = null,

	@field:SerializedName("value")
	val value: Int? = null
)

data class EkartuResultItem(

	@field:SerializedName("cust_usr_tgl_lahir")
	val custUsrTglLahir: String? = null,

	@field:SerializedName("cust_usr_id")
	val custUsrId: String? = null,

	@field:SerializedName("cust_usr_kode")
	val custUsrKode: String? = null,

	@field:SerializedName("cust_usr_nama")
	val custUsrNama: String? = null
)
