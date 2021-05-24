package com.itrsiam.rsiamuslimat.list_tiket

import android.util.Log
import com.itrsiam.rsiamuslimat.api.NetWorkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TiketPresenter (val tiketView: TiketView) {
    fun getTiket(id_login:String){
        NetWorkConfig.getServicee()
            .get_Tiket(id_login)
            .enqueue(object : Callback<TiketResponse>{
                override fun onFailure(call: Call<TiketResponse>, t: Throwable) {
                    tiketView.onFailedGetTiket("Gagal")
                    Log.d("Error", "Error Data")
                }

                override fun onResponse(
                    call: Call<TiketResponse>,
                    response: Response<TiketResponse>
                ) {
                    if (response.isSuccessful){
                        val status= response.body()?.value
                        if (status==1){
                            val results= response.body()!!.result
                            tiketView.onGetTiket(results)
                        }
                        else{
                            tiketView.onFailedGetTiket("Error $status")
                        }
                    }

                }

            })
    }



    fun getBatalTiket(id_login:String){
        NetWorkConfig.getServicee()
            .get_batal_Tiket(id_login)
            .enqueue(object : Callback<TiketResponse>{
                override fun onFailure(call: Call<TiketResponse>, t: Throwable) {
                    tiketView.onFailedGetTiket("Gagal")
                    Log.d("Error", "Error Data")
                }

                override fun onResponse(
                    call: Call<TiketResponse>,
                    response: Response<TiketResponse>
                ) {
                    if (response.isSuccessful){
                        val status= response.body()?.value
                        if (status==1){
                            val results= response.body()!!.result
                            tiketView.onGetTiket(results)
                        }
                        else{
                            tiketView.onFailedGetTiket("Error $status")
                        }
                    }

                }

            })
    }

    fun batal_reg(id_login:String){
        NetWorkConfig.getServicee()
            .batal_reg(id_login)
            .enqueue(object : Callback<TiketResponse>{
                override fun onFailure(call: Call<TiketResponse>, t: Throwable) {

                        tiketView.onFailed(t.localizedMessage)
                        Log.d("Error", "Error Data")
                    }

                    override fun onResponse(
                        call: Call<TiketResponse>,
                        response: Response<TiketResponse>
                    ) {
                        if (response.isSuccessful){
                            val status= response.body()?.value
                            if (status==202){

                                tiketView.onGet("Pembatalan Berhasil")
                            }
                            else{
                                tiketView.onFailed("Gagal")
                            }
                        }

                    }

                })
    }

}