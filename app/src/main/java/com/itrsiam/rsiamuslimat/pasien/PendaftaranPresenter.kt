package com.itrsiam.rsiamuslimat.pasien

import com.itrsiam.rsiamuslimat.api.NetWorkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PendaftaranPresenter(val pendaftaranView: PendaftaranView) {

   fun regis_antrian(id_dokter:String?,reg_buffer_jenis_pasien:String?,reg_buffer_id_login:String?,
    poli_id:String?,nama_dokter:String?,nm_poli:String?,reg_buffer_nobpjs:String?,asuransi_no:String?,
    asuransi_id:String?,reg_buffer_tanggal:String?,jam:String?,keluhan:String?,tujuan_periksa:String?,
                     keluhan_lain:String?,tujuan_lain:String?,pasienid:String?,rujukan:String?,id_jadwal:String?,no_hp:String?) {
       NetWorkConfig.getServicee()
           .regis_pasien(id_dokter,reg_buffer_jenis_pasien,reg_buffer_id_login,
               poli_id,nama_dokter,nm_poli,reg_buffer_nobpjs,asuransi_no,asuransi_id,reg_buffer_tanggal,jam,
           keluhan,tujuan_periksa,keluhan_lain,tujuan_lain,pasienid,rujukan,id_jadwal,no_hp)
           .enqueue(object : Callback<PendaftaranResponse>{
               override fun onFailure(call: Call<PendaftaranResponse>, t: Throwable) {
                   pendaftaranView.onFailedRPendaftaran(t.localizedMessage)
               }

               override fun onResponse(
                   call: Call<PendaftaranResponse>,
                   response: Response<PendaftaranResponse>
               ) {
                   if (response.body()?.value ==1){
                       pendaftaranView.onSuccessPendaftaran(response.body()!!.message
                       , response.body()!!.reg_buffer_id, response.body()!!.noAntrian)
                   }
                   else if (response.body()?.value==2){
                       pendaftaranView.onFullRPendaftaran(response.body()!!.message)
                   }
                   else if (response.body()?.value==3){
                       pendaftaranView.onFullRPendaftaran(response.body()!!.message)
                   }
                   else{
                       pendaftaranView.onFailedRPendaftaran(response.body()?.message)
                   }

               }

           })

   }

}