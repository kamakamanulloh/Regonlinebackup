package com.itrsiam.rsiamuslimat.pengingat_kontrol

import com.itrsiam.rsiamuslimat.api.NetWorkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PengingatKontrolPresenter (val pengingatKontrolView: PengingatKontrolView){
    fun list_pengingatKontrol(no_rm:String?){
        NetWorkConfig.getServicee()
            .list_kontrol(no_rm)
            .enqueue(object : Callback<PengingatKontrolResponse>{
                override fun onResponse(
                    call: Call<PengingatKontrolResponse>,
                    response: Response<PengingatKontrolResponse>
                ) {
                    val responses=response.body()
                    if (responses?.value==200){
                        pengingatKontrolView.onSuccessKontrol(responses.result)
                    }
                    else{
                        pengingatKontrolView.onFailedKontrol("Data Kosong")
                    }
                }

                override fun onFailure(call: Call<PengingatKontrolResponse>, t: Throwable) {
                    pengingatKontrolView.onFailedKontrol("Koneksi Gagal")
                }

            })

    }
}