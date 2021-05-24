package com.itrsiam.rsiamuslimat.pasien

import com.google.gson.annotations.SerializedName

data class NoAntrianResponse(

	@field:SerializedName("nama_px")
	val namaPx: String? = null,

	@field:SerializedName("reg_buffer_no_antrian")
	val regBufferNoAntrian: String? = null,

	@field:SerializedName("pasien_nama")
	val pasienNama: String? = null,

	@field:SerializedName("poli_nama")
	val poliNama: String? = null,

	@field:SerializedName("login_cust_phone_number")
	val loginCustPhoneNumber: String? = null,

	@field:SerializedName("jenis_nama")
	val jenisNama: String? = null,

	@field:SerializedName("reg_buffer_waktu")
	val regBufferWaktu: String? = null,

	@field:SerializedName("perusahaan_nama")
	val perusahaanNama: String? = null,

	@field:SerializedName("reg_buffer_id")
	val regBufferId: String? = null,

	@field:SerializedName("reg_buffer_tanggal")
	val regBufferTanggal: String? = null,

	@field:SerializedName("usr_name")
	val usrName: String? = null,

	@field:SerializedName("no_rm")
	val noRm: String? = null,

	@field:SerializedName("reg_buffer_nobpjs")
	val regBufferNobpjs: String? = null,

	@field:SerializedName("value")
	val value: Int? = null
)
