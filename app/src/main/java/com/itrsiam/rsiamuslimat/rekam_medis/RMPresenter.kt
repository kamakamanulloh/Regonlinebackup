package com.itrsiam.rsiamuslimat.rekam_medis

import com.itrsiam.rsiamuslimat.api.NetWorkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RMPresenter (val rmView: RMView) {
    fun cekrm(norm: String, tgl: String) {
        NetWorkConfig.getServicee()
            .rekam_medis(norm, tgl)
            .enqueue(object : Callback<RMResponse> {
                override fun onFailure(call: Call<RMResponse>, t: Throwable) {
                    rmView.onFailedGetRM(t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<RMResponse>,
                    response: Response<RMResponse>
                ) {
                    if (response.body()?.value == 202) {
                        val results = response.body()!!.result
                        rmView.onGetRM(results)
                    } else {
                        rmView.onFailedGetRM("Error ")
                    }

                }

            })


    }
}