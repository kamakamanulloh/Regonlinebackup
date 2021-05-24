package com.itrsiam.rsiamuslimat.lupa_rm

import com.itrsiam.rsiamuslimat.api.NetWorkConfig
import com.itrsiam.rsiamuslimat.pasien.PasienResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LupaRmPresenter(val lupaRmView: LupaRmView) {
    fun pulihkan_rm(isian : String,jenis_id : String){
        NetWorkConfig.getServicee()
            .lupa_rm(isian,jenis_id)
            .enqueue(object : Callback<PasienResponse> {
                override fun onFailure(call: Call<PasienResponse>, t: Throwable) {
                    lupaRmView.onFailure("Failure")
                }

                override fun onResponse(
                    call: Call<PasienResponse>,
                    response: Response<PasienResponse>
                ) {
                    if (response.body()?.value == 202){
                        lupaRmView.onSuccessLogin(response.body()?.message,response.body()?.cust_usr_id
                            ,response.body()?.pasien_rm,response.body()?.pasien_nama,response.body()?.pasien_tgllahir
                            ,response.body()?.pasien_alamat, response.body()?.cust_usr_no_identitas,response.body()?.cust_usr_no_jaminan)
                    }
                    else{
                        lupaRmView.onFailedLogin(response.body()?.message)
                    }

                }

            })

    }

}