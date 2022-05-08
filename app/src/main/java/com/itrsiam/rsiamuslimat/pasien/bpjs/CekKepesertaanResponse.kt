package com.itrsiam.rsiamuslimat.pasien.bpjs

import com.google.gson.annotations.SerializedName

data class CekKepesertaanResponse(

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("response")
	val response: Response? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class JenisPeserta(

	@field:SerializedName("keterangan")
	val keterangan: String? = null,

	@field:SerializedName("kode")
	val kode: String? = null
)

data class Peserta(

	@field:SerializedName("tglTAT")
	val tglTAT: String? = null,

	@field:SerializedName("statusPeserta")
	val statusPeserta: StatusPeserta? = null,

	@field:SerializedName("provUmum")
	val provUmum: ProvUmum? = null,

	@field:SerializedName("umur")
	val umur: Umur? = null,

	@field:SerializedName("mr")
	val mr: Mr? = null,

	@field:SerializedName("sex")
	val sex: String? = null,

	@field:SerializedName("tglCetakKartu")
	val tglCetakKartu: String? = null,

	@field:SerializedName("tglTMT")
	val tglTMT: String? = null,

	@field:SerializedName("hakKelas")
	val hakKelas: HakKelas? = null,

	@field:SerializedName("nik")
	val nik: String? = null,

	@field:SerializedName("tglLahir")
	val tglLahir: String? = null,

	@field:SerializedName("pisa")
	val pisa: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("cob")
	val cob: Cob? = null,

	@field:SerializedName("noKartu")
	val noKartu: String? = null,

	@field:SerializedName("jenisPeserta")
	val jenisPeserta: JenisPeserta? = null,

	@field:SerializedName("informasi")
	val informasi: Informasi? = null
)

data class Informasi(

	@field:SerializedName("prolanisPRB")
	val prolanisPRB: Any? = null,

	@field:SerializedName("noSKTM")
	val noSKTM: Any? = null,

	@field:SerializedName("dinsos")
	val dinsos: Any? = null
)

data class StatusPeserta(

	@field:SerializedName("keterangan")
	val keterangan: String? = null,

	@field:SerializedName("kode")
	val kode: String? = null
)

data class Umur(

	@field:SerializedName("umurSekarang")
	val umurSekarang: String? = null,

	@field:SerializedName("umurSaatPelayanan")
	val umurSaatPelayanan: String? = null
)

data class Mr(

	@field:SerializedName("noTelepon")
	val noTelepon: String? = null,

	@field:SerializedName("noMR")
	val noMR: String? = null
)

data class Cob(

	@field:SerializedName("tglTAT")
	val tglTAT: Any? = null,

	@field:SerializedName("nmAsuransi")
	val nmAsuransi: Any? = null,

	@field:SerializedName("noAsuransi")
	val noAsuransi: Any? = null,

	@field:SerializedName("tglTMT")
	val tglTMT: Any? = null
)

data class HakKelas(

	@field:SerializedName("keterangan")
	val keterangan: String? = null,

	@field:SerializedName("kode")
	val kode: String? = null
)

data class ProvUmum(

	@field:SerializedName("nmProvider")
	val nmProvider: String? = null,

	@field:SerializedName("kdProvider")
	val kdProvider: String? = null
)

data class Response(

	@field:SerializedName("peserta")
	val peserta: Peserta? = null
)
