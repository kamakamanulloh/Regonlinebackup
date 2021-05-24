package com.itrsiam.rsiamuslimat.list_tiket

import com.google.gson.annotations.SerializedName
import java.io.Serializable
data class TiketResponse(

	@field:SerializedName("result")
	val result: List<TiketResults?>? = null,

	@field:SerializedName("message")
	val message:String?=null,

	@field:SerializedName("value")
	val value: Int? = null
)

 class TiketResults :Serializable {

	 @field:SerializedName("total_antrian")
	 val totalAntrian: String? = null

	 @field:SerializedName("reg_buffer_no_antrian")
	 val regBufferNoAntrian: String? = null

	 @field:SerializedName("reg_buffer_status")
	 val regBufferStatus: String? = null

	 @field:SerializedName("reg_buffer_batal")
	 val reg_buffer_batal: String? = null

	 @field:SerializedName("poli_nama")
	 val poliNama: String? = null

	 @field:SerializedName("login_cust_phone_number")
	 val loginCustPhoneNumber: String? = null

	 @field:SerializedName("jenis_nama")
	 val jenisNama: String? = null

	 @field:SerializedName("cust_usr_kode")
	 val custUsrKode: String? = null

	 @field:SerializedName("reg_buffer_waktu")
	 val regBufferWaktu: String? = null

	 @field:SerializedName("perusahaan_nama")
	 val perusahaanNama: Any? = null

	 @field:SerializedName("cust_usr_nama")
	 val custUsrNama: String? = null

	 @field:SerializedName("reg_buffer_id")
	 val regBufferId: String? = null

	 @field:SerializedName("reg_buffer_tanggal")
	 val regBufferTanggal: String? = null

	 @field:SerializedName("usr_name")
	 val usrName: String? = null

	 @field:SerializedName("reg_buffer_nobpjs")
	 val regBufferNobpjs: Any? = null

	 @field:SerializedName("jadwal_dokter_status")
	 val jadwalDokterStatus: Any? = null
 }
