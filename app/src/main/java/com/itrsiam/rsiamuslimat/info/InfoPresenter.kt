package com.itrsiam.rsiamuslimat.info

import com.itrsiam.rsiamuslimat.api.NetWorkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoPresenter(val infoView: InfoView) {
    fun getInfo(){
        NetWorkConfig.getServicee()
            .get_info()
            .enqueue(object : Callback<InfoResponse>{
                override fun onFailure(call: Call<InfoResponse>, t: Throwable) {
                    infoView.onFailedInfo("Gagal")
                }

                override fun onResponse(
                    call: Call<InfoResponse>,
                    response: Response<InfoResponse>
                ) {
                   if (response.isSuccessful){
                       val value= response.body()?.value
                       if (value==202){
                           val data = response.body()?.result
                           infoView.onSuccessInfo(data)
                       }
                       else{
                           infoView.onFailedInfo("Cek Koneksi Internet")
                       }
                   }
                }

            })
    }
    fun getInfoTerbaru(){
        NetWorkConfig.getServicee()
            .get_info_baru()
            .enqueue(object : Callback<InfoResponse>{
                override fun onResponse(
                    call: Call<InfoResponse>,
                    response: Response<InfoResponse>
                ) {
                    if (response.isSuccessful){
                        val value=response.body()?.value
                        if (value==202){
                            val data=response.body()?.result
                            infoView.onSuccessInfo((data))
                        }

                    }
                }

                override fun onFailure(call: Call<InfoResponse>, t: Throwable) {

                }

            })
    }
}