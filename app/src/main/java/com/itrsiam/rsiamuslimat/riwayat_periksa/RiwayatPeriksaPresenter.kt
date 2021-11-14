package com.itrsiam.rsiamuslimat.riwayat_periksa

import com.itrsiam.rsiamuslimat.api.NetWorkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RiwayatPeriksaPresenter(val riwayatPeriksaView: RiwayatPeriksaView) {
    fun listPeriksa(no_rm:String?){
        NetWorkConfig.getServicee()
            .list_periksa(no_rm)
            .enqueue(object :Callback<RiwayatPeriksaResponse>{
                override fun onResponse(
                    call: Call<RiwayatPeriksaResponse>,
                    response: Response<RiwayatPeriksaResponse>
                ) {
                    val responses=response.body()
                    if (responses?.value==200){
                        val result=responses.result
                        riwayatPeriksaView.onSucccessPeriksa(result)
                    }
                    else{
                        riwayatPeriksaView.onFailedPeriksa("Data Tidak Ditemukan")
                    }

                }

                override fun onFailure(call: Call<RiwayatPeriksaResponse>, t: Throwable) {
                    riwayatPeriksaView.onFailedPeriksa("Gagal")

                }

            })

    }
}