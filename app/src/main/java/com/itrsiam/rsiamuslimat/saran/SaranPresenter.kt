package com.itrsiam.rsiamuslimat.saran

import com.itrsiam.rsiamuslimat.api.NetWorkConfig
import com.itrsiam.rsiamuslimat.registrasi.RegisterResults
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class SaranPresenter(val saranView: SaranView) {
    fun insSaran( user_id:String?, saran:String?){
        NetWorkConfig.getServicee()
            .insert_saran(user_id,saran)
            .enqueue(object : Callback<RegisterResults>{
                override fun onFailure(call: Call<RegisterResults>, t: Throwable) {
                    saranView.onFailedIns("gagal")
                }

                override fun onResponse(
                    call: Call<RegisterResults>,
                    response: Response<RegisterResults>
                ) {
                   if (response.isSuccessful){
                       val value=response.body()?.value
                       if (value==202){
                           saranView.onSuccessIns(response.body()!!.message)
                       }
                       else{
                           saranView.onFailedIns("Cek Koneksi Internet")
                       }
                   }
                }

            })
    }

}