package com.itrsiam.rsiamuslimat.cari_dokter

import com.google.gson.annotations.SerializedName

data class DokterResponse(

	@field:SerializedName("result")
	val result: List<ResultItemDokter?>? = null,

	@field:SerializedName("value")
	val value: Int? = null
)

data class ResultItemDokter(

	@field:SerializedName("usr_foto")
	val usrFoto: String? = null,

	@field:SerializedName("usr_id")
	val usrId: String? = null,

	@field:SerializedName("dokter_nama")
	val dokterNama: String? = null,

	@field:SerializedName("id_dokter")
	val idDokter: String? = null
)
