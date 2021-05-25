package com.itrsiam.rsiamuslimat.api

import com.itrsiam.rsiamuslimat.cari_dokter.DetailDokterResponse
import com.itrsiam.rsiamuslimat.cari_dokter.DokterResponse
import com.itrsiam.rsiamuslimat.cek_antrian.CekAntrianResponse
import com.itrsiam.rsiamuslimat.info.InfoResponse
import com.itrsiam.rsiamuslimat.jadwal_dokter.JadwalResponse
import com.itrsiam.rsiamuslimat.jumlah.JumlahResponse
import com.itrsiam.rsiamuslimat.kartu.EkartuResponse
import com.itrsiam.rsiamuslimat.kartu.KartuResponse
import com.itrsiam.rsiamuslimat.list_tiket.TiketResponse
import com.itrsiam.rsiamuslimat.login.LoginResponse
import com.itrsiam.rsiamuslimat.pasien.NoAntrianResponse
import com.itrsiam.rsiamuslimat.pasien.PasienResponse
import com.itrsiam.rsiamuslimat.pasien.PendaftaranResponse
import com.itrsiam.rsiamuslimat.pasien.asuransi.AsuransiResponse
import com.itrsiam.rsiamuslimat.pasien.bpjs.CekKepesertaanResponse
import com.itrsiam.rsiamuslimat.pasien.bpjs.TipejknResponse
import com.itrsiam.rsiamuslimat.poli.PoliResponse

import com.itrsiam.rsiamuslimat.registrasi.RegisterResults
import com.itrsiam.rsiamuslimat.rekam_medis.RMResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

object NetWorkConfig {
    fun getInterceptor(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return okHttpClient
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://rsiamuslimat.co.id/admin/index.php/API/ServerApi/")
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }



    fun getRetrofitt(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://103.53.78.78/api_regonline/")
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService() = getRetrofit().create(ApiService::class.java)
    fun getServicee() = getRetrofitt().create(ApiService::class.java)
}
    interface ApiService{
//        @GET("getinfo")
//        fun getInfo(): retrofit2.Call<InfoResponse>


        @FormUrlEncoded
        @POST("registrasi.php")
        fun register(
            @Field("username") username : String?,
            @Field("nama_depan")nama_depan : String?,
            @Field("nama_belakang")nama_belakang : String?,
            @Field("password")password : String?,
            @Field("no_hp")nohp : String?
        ):
                retrofit2.Call<RegisterResults>


        @FormUrlEncoded
        @POST("edit_profil.php")
        fun update_profil(
            @Field("login_cust_id")login_cust_id : String?,
            @Field("username") username : String?,
            @Field("nama_depan")nama_depan : String?,
            @Field("nama_belakang")nama_belakang : String?,

            @Field("no_hp")nohp : String?
        ):
                retrofit2.Call<LoginResponse>

        @FormUrlEncoded
        @POST("update_password.php")
        fun update_password(
            @Field("password")password : String?,
            @Field("login_cust_id")login_cust_id : String?
        ):
                retrofit2.Call<LoginResponse>

        @FormUrlEncoded
        @POST("batal_reg.php")
        fun batal_reg(

            @Field("kd_tiket")login_cust_id : String?
        ):
                retrofit2.Call<TiketResponse>


        @FormUrlEncoded
        @POST("detail_dokter.php")
        fun detail_dokter(

            @Field("usr_id")usr_id : String?
        ):
                retrofit2.Call<DetailDokterResponse>

        @FormUrlEncoded
        @POST("login.php")
        fun login(
            @Field("username")username: String?,
            @Field("password")password: String?
        ):
                retrofit2.Call<LoginResponse>

        @GET("profil.php?user_id=")
        fun profil(
            @Query("user_id")user_id: String?
        ):
                retrofit2.Call<LoginResponse>

        @FormUrlEncoded
        @POST("cek.php")
        fun cek_rm(
            @Field("pasien_rm")pasien_rm: String?,
            @Field("tgl_lahir")tgl_lahir: String?

        ):
                retrofit2.Call<PasienResponse>

        @FormUrlEncoded
        @POST("lupa_rm.php")
        fun lupa_rm(
            @Field("isian")isian: String?,
            @Field("jenis_id")jenis_id: String?

        ):
                retrofit2.Call<PasienResponse>


        @FormUrlEncoded
        @POST("rekam_medis.php")
        fun rekam_medis(
            @Field("pasien_rm")pasien_rm: String?,
            @Field("tgl_lahir")tgl_lahir: String?

        ):
                retrofit2.Call<RMResponse>


        @FormUrlEncoded
        @POST("insert_saran.php")
        fun insert_saran(
            @Field("user_id")user_id: String?,
            @Field("saran")saran:String?
        ):
                retrofit2.Call<RegisterResults>


        @GET("list_poli.php")
        fun get_poli(

        ):
                retrofit2.Call<PoliResponse>

        @GET("list_info.php")
        fun get_info(

        ):
                retrofit2.Call<InfoResponse>

        @GET("list_all_dokter.php")
        fun get_dokter(

        ):
                retrofit2.Call<DokterResponse>

        @FormUrlEncoded
        @POST("cariDokter.php")
        fun get_CariDokter(
            @Field("nmDokter")nmDokter:String?

        ):
                Call<DokterResponse>

        @FormUrlEncoded
        @POST("detail_dokter.php")
        fun get_DetailDokter(
            @Field("usr_id")usr_id:String?

        ):
                Call<DokterResponse>

        @FormUrlEncoded
        @POST("list_jadwal.php")
        fun get_jadwal(
            @Field("id_poli")id_poli:String?,
            @Field("tanggal")tanggal:String?
        ):
                retrofit2.Call<JadwalResponse>

        @FormUrlEncoded
        @POST("regis_antrian.php")
        fun regis_pasien(
            @Field("id_dokter")id_dokter:String?,
            @Field("reg_buffer_jenis_pasien")reg_buffer_jenis_pasien:String?,
            @Field("reg_buffer_id_login")reg_buffer_id_login:String?,
            @Field("poli_id")poli_id:String?,
            @Field("nama_dokter")nama_dokter:String?,
            @Field("nm_poli")nm_poli:String?,
            @Field("reg_buffer_nobpjs")reg_buffer_nobpjs:String?,
            @Field("asuransi_no")asuransi_no:String?,
            @Field("asuransi_id")asuransi_id:String?,
            @Field("reg_buffer_tanggal")reg_buffer_tanggal:String?,
            @Field("jam")jam:String?,
            @Field("keluhan")keluhan:String?,
            @Field("tujuan_periksa")tujuan_periksa:String?,
            @Field("keluhan_lain")keluhan_lain:String?,
            @Field("tujuan_lain")tujuan_lain:String?,
            @Field("cust_usr_id")cust_usr_id:String?,
            @Field("reg_buffer_no_rujukan")reg_buffer_no_rujukan:String?,
            @Field("id_jadwal")id_jadwal:String?
        ):
                retrofit2.Call<PendaftaranResponse>

        @FormUrlEncoded
        @POST("tiketView.php")
        fun getNoAntrian(
            @Field("reg_buffer_id_login") reg_buffer_id_login:String?,
            @Field("now") now:String?
        ):retrofit2.Call<NoAntrianResponse>


        @GET("list_asuransi.php")
        fun get_Asuransi():retrofit2.Call<AsuransiResponse>

        @GET("tipe_jkn.php")
        fun get_Jkn():retrofit2.Call<TipejknResponse>

        @GET("kuota.php?id_jadwal=")
        fun get_Kuota(
            @Query("id_jadwal")id_jadwal: String?
        ):retrofit2.Call<JumlahResponse>

        @GET("history.php?id_login=")
        fun get_Tiket(
            @Query("id_login")id_login: String?
        ):retrofit2.Call<TiketResponse>

        @GET("bpjs.php")
        fun get_Kepesertaan(
            @Query("param")param:String?,
            @Query("tgl")tgl:String?
        ):retrofit2.Call<CekKepesertaanResponse>


        @GET("history_batal.php?id_login=")
        fun get_batal_Tiket(
            @Query("id_login")id_login: String?
        ):retrofit2.Call<TiketResponse>

        @FormUrlEncoded
        @POST("antrianPoli.php")
        fun getAntrianPoli(
            @Field("idPoli")idPoli:String?,
            @Field("idDokter")idDokter:String?

        ):Call<CekAntrianResponse>
        @FormUrlEncoded
        @POST("add_kartu.php")
        fun addKartu(
            @Field("pasien_rm")pasien_rm: String?,
            @Field("tanggal_lahir")tanggal_lahir: String?,
            @Field("id_login")id_login: String?
        ):Call<KartuResponse>

        @FormUrlEncoded
        @POST("rm_kartu.php")
        fun rm_kartu(

            @Field("id_login")usr_id : String?
        ):
                retrofit2.Call<EkartuResponse>

        @FormUrlEncoded
        @POST("hapus_kartu.php")
        fun hapus_kartu(

            @Field("pasien_rm")pasien_rm : String?,
            @Field("id_login")id_login : String?
        ):
                retrofit2.Call<EkartuResponse>

    }

