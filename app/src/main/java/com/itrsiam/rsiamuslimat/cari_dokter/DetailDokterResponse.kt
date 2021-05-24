package com.itrsiam.rsiamuslimat.cari_dokter

import com.google.gson.annotations.SerializedName

data class DetailDokterResponse(

	@field:SerializedName("result")
	val result: List<ResultItemDetailDokter?>? = null,

	@field:SerializedName("value")
	val value: Int? = null
)

data class ResultItemDetailDokter(

	@field:SerializedName("jadwal_dokter_id")
	val jadwalDokterId: String? = null,

	@field:SerializedName("jadwal_dokter_hari")
	val jadwalDokterHari: String? = null,

	@field:SerializedName("poli_nama")
	val poliNama: String? = null,

	@field:SerializedName("usr_foto")
	val usrFoto: String? = null,

	@field:SerializedName("jadwal_dokter_jam_selesai")
	val jadwalDokterJamSelesai: String? = null,

	@field:SerializedName("poli_id")
	val poliId: String? = null,

	@field:SerializedName("kuota")
	val kuota: String? = null,

	@field:SerializedName("dokter_nama")
	val dokterNama: String? = null,

	@field:SerializedName("jadwal_dokter_jam_mulai")
	val jadwalDokterJamMulai: String? = null,

	@field:SerializedName("id_dokter")
	val idDokter: String? = null
)
