package com.itrsiam.rsiamuslimat.pasien.asuransi

import android.util.Log
import com.itrsiam.rsiamuslimat.api.NetWorkConfig
import retrofit2.Call
import retrofit2.Response

class AsuransiPresenter(val asuransiView: AsuransiView) {
    fun getAsuransi() {
        NetWorkConfig.getServicee()
            .get_Asuransi()
            .enqueue(object : retrofit2.Callback<AsuransiResponse> {
                override fun onFailure(call: Call<AsuransiResponse>, t: Throwable) {
                    asuransiView.onFailedGetAsuransi(t.localizedMessage)
                    Log.d("Error", "Error Data")

                }

                override fun onResponse(
                    call: Call<AsuransiResponse>,
                    response: Response<AsuransiResponse>
                ) {
                    if(response.isSuccessful){
                        val status = response.body()?.value
                        if (status == 1){
                            val data = response.body()?.result
                            asuransiView.onSuccessGetAsuransi(data)
                        } else{
                            asuransiView.onFailedGetAsuransi("Error $status")
                        }
                    }

                }

            })
    }
}