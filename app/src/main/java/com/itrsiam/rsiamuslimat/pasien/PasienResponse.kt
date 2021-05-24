package com.itrsiam.rsiamuslimat.pasien

import com.google.gson.annotations.SerializedName

data class PasienResponse(

	@field:SerializedName("value")
	val value: Int? = null,
	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("cust_usr_id")
	val cust_usr_id: String? = null,
	@field:SerializedName("pasien_rm")
	val pasien_rm: String? = null,
	@field:SerializedName("pasien_nama")
	val pasien_nama: String? = null,
	@field:SerializedName("pasien_alamat")
	val pasien_alamat: String? = null,
	@field:SerializedName("pasien_nohp")
	val pasien_nohp: String? = null,
	@field:SerializedName("pasien_tgllahir")
	val pasien_tgllahir: String? = null,
	@field:SerializedName("cust_usr_no_identitas")
	val cust_usr_no_identitas: String? = null,
	@field:SerializedName("cust_usr_no_jaminan")
	val cust_usr_no_jaminan:String?=null

)
