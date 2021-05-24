package com.itrsiam.rsiamuslimat.jadwal_dokter

import com.google.gson.annotations.SerializedName

data class JadwalResponse(

	@field:SerializedName("result")
	val result: List<JadwalResultItem?>? = null,

	@field:SerializedName("value")
	val value: Int? = null
)

data class JadwalResultItem(

	@field:SerializedName("jadwal_dokter_id")
	val jadwalDokterId: String? = null,

	@field:SerializedName("jadwal_dokter_hari")
	val jadwalDokterHari: String? = null,

	@field:SerializedName("jadwal_dokter_jam_selesai")
	val jadwalDokterJamSelesai: String? = null,

	@field:SerializedName("poli_id")
	val poliId: String? = null,

	@field:SerializedName("dokter_nama")
	val dokterNama: String? = null,

	@field:SerializedName("jadwal_dokter_jam_mulai")
	val jadwalDokterJamMulai: String? = null,

	@field:SerializedName("id_dokter")
	val id_dokter: String? = null,
	@field:SerializedName("poli_nama")
	val poli_nama: String? = null,
	@field:SerializedName("kuota")
	val kuota: Int? = null,
	@field:SerializedName("usr_foto")
	val usr_foto: String? = null
)
