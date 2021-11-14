package com.itrsiam.rsiamuslimat.laboratorium

import com.itrsiam.rsiamuslimat.api.NetWorkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LabPresenter(val labView: LabView) {
    fun listLab(no_rm:String?){
        NetWorkConfig.getServicee()
            .listLab(no_rm)
            .enqueue(object :Callback<LabResponse>{
                override fun onResponse(call: Call<LabResponse>, response: Response<LabResponse>) {
                    val responses=response.body()
                    if (responses?.value==200){
                        val result= responses.result
                        labView.onSuccessLab(result)

                    }
                    else{
                        labView.onFailedLab("Data Tidak Ditemukan")
                    }
                }

                override fun onFailure(call: Call<LabResponse>, t: Throwable) {
                    labView.onFailedLab("Koneksi Gagal")
                }

            })
    }
}