package com.itrsiam.rsiamuslimat.diagnosa

import com.itrsiam.rsiamuslimat.api.NetWorkConfig
import retrofit2.Callback
import retrofit2.Call

import retrofit2.Response
class DiagnosPresenter(val diagnosaView: DiagnosaView) {
    fun list_diagnosa(no_rm:String?){
        NetWorkConfig.getServicee()
            .list_diagnosa(no_rm)
            .enqueue(object :Callback<DiagnosaResponse>{
                override fun onResponse(
                    call: Call<DiagnosaResponse>,
                    response: Response<DiagnosaResponse>
                ) {
                   val responses=response.body()
                    if (responses?.value==200){
                        val result= responses.result
                        diagnosaView.onSuccessDiagnosa(result)
                    }
                    else{
                        diagnosaView.onFailedDiagnosa("Data Tidak Ditemukan")
                    }
                }

                override fun onFailure(call: Call<DiagnosaResponse>, t: Throwable) {
                    diagnosaView.onFailedDiagnosa("Koneksi Gagal")
                }

            })
    }
}