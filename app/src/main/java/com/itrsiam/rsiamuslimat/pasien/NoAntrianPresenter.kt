package com.itrsiam.rsiamuslimat.pasien

import com.itrsiam.rsiamuslimat.api.NetWorkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoAntrianPresenter(val noAntrianView: NoAntrianView) {

    fun getNoAntrian(idLogin:String?,now:String?){
        NetWorkConfig.getServicee()
            .getNoAntrian(idLogin,now)
            .enqueue(object :Callback<NoAntrianResponse>{
                override fun onResponse(
                    call: Call<NoAntrianResponse>,
                    response: Response<NoAntrianResponse>
                ) {
                  if (response.body()?.value ==202){
                      noAntrianView.onSucces(response.body()?.namaPx,response.body()?.regBufferNoAntrian,response.body()?.pasienNama
                          ,response.body()?.poliNama,response.body()?.loginCustPhoneNumber,response.body()?.jenisNama,response.body()?.regBufferWaktu,
                          response.body()?.perusahaanNama,response.body()?.regBufferId,response.body()?.regBufferTanggal,response.body()?.usrName,
                          response.body()?.noRm, response.body()?.regBufferNobpjs);
                  }
                    else{
                      noAntrianView.onFailedGet("Tidak ditemukan")
                  }
                }

                override fun onFailure(call: Call<NoAntrianResponse>, t: Throwable) {
                    noAntrianView.onFailure("Failure")
                }

            })
    }


}