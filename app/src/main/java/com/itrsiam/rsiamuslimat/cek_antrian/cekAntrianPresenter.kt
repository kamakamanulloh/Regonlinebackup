package com.itrsiam.rsiamuslimat.cek_antrian

import com.itrsiam.rsiamuslimat.api.NetWorkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class cekAntrianPresenter(val cekAntrianView: CekAntrianView) {

    fun getAntrian(idPoli:String,idDokter:String){
        NetWorkConfig.getServicee()
            .getAntrianPoli(idPoli,idDokter)
            .enqueue(object :Callback<CekAntrianResponse>{



                override fun onResponse(
                    call: Call<CekAntrianResponse>,
                    response: Response<CekAntrianResponse>
                ) {

                    if (response.isSuccessful){
                        val status=response.body()?.value
                        if (status==202){
                           cekAntrianView.onSuccessAntrian(response?.body()?.regNoAntrian,response?.body()?.poliNama,response?.body()?.dokterNama)
                        }
                        else{
                            cekAntrianView.onFailedAntri("Cek Koneksi Internet")
                        }

                    }


                }

                override fun onFailure(call: Call<CekAntrianResponse>, t: Throwable) {

                    cekAntrianView.onFailedAntri("Cek Koneksi Internet")

                }

            })
    }
}