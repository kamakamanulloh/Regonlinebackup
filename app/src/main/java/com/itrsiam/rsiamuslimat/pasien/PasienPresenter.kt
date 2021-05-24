package com.itrsiam.rsiamuslimat.pasien

import com.itrsiam.rsiamuslimat.api.NetWorkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PasienPresenter(val cekRMView: CekRMView) {
    fun cekrm(norm : String,tgl : String){
        NetWorkConfig.getServicee()
            .cek_rm(norm,tgl)
            .enqueue(object : Callback<PasienResponse>{
                override fun onFailure(call: Call<PasienResponse>, t: Throwable) {
                    cekRMView.onFailure("Failure")
                }

                override fun onResponse(
                    call: Call<PasienResponse>,
                    response: Response<PasienResponse>) {
                    if (response.body()?.value == 202){
                        cekRMView.onSuccessLogin(response.body()?.message,response.body()?.cust_usr_id
                        ,response.body()?.pasien_rm,response.body()?.pasien_nama,response.body()?.pasien_tgllahir
                        ,response.body()?.pasien_alamat, response.body()?.cust_usr_no_identitas,response.body()?.cust_usr_no_jaminan)
                    }
                    else{
                        cekRMView.onFailedLogin("Cek Koneksi Internet")
                    }

                }

            })

    }



}