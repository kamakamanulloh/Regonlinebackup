package com.itrsiam.rsiamuslimat.pasien.bpjs

import com.itrsiam.rsiamuslimat.api.NetWorkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JknPresenter(val tipeJKNView: TipeJKNView) {
    fun getjkn(){
        NetWorkConfig.getServicee()
            .get_Jkn()
            .enqueue(object : Callback<TipejknResponse>{
                override fun onFailure(call: Call<TipejknResponse>, t: Throwable) {
                    tipeJKNView.onFailedGetjkn(t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<TipejknResponse>,
                    response: Response<TipejknResponse>
                ) {
                    if (response.body()?.value == 202){
                        val data = response.body()?.result
                        tipeJKNView.onSuccessGetjkn(data)
                    }
                    else{
                        tipeJKNView.onFailedGetjkn("Error")
                    }
                }

            })
    }
}