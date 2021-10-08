package com.itrsiam.rsiamuslimat.radiologi

import com.itrsiam.rsiamuslimat.api.NetWorkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RadiologiPresenter(val radiologiView: RadiologiView) {
    fun listRad(no_rm:String?){
        NetWorkConfig.getServicee()
            .listRad(no_rm)
            .enqueue(object :Callback<RadilogiResponse>{
                override fun onResponse(
                    call: Call<RadilogiResponse>,
                    response: Response<RadilogiResponse>
                ) {
                    val responses=response.body()
                    if (responses?.value==200){
                        val result=responses!!.result
                        radiologiView.onSuccessRadiologi(result)

                    }
                    else{
                        radiologiView.onFailedRadiologi("Data Tidak Ditemukan")
                    }

                }

                override fun onFailure(call: Call<RadilogiResponse>, t: Throwable) {
                    radiologiView.onFailedRadiologi("Data Tidak Ditemukan") }

            })
    }
}