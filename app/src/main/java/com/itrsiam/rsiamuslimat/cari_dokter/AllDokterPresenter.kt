package com.itrsiam.rsiamuslimat.cari_dokter

import android.util.Log
import com.itrsiam.rsiamuslimat.api.NetWorkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllDokterPresenter(val allDokterView: AllDokterView) {
    fun getAllDokter(){
        NetWorkConfig.getServicee()
            .get_dokter()
            .enqueue(object :Callback<DokterResponse>{
                override fun onFailure(call: Call<DokterResponse>, t: Throwable) {
                    allDokterView.onFailedGetDokter("Koneksi Internet Bermasalah")
                    Log.d("Error","Error Data")
                }

                override fun onResponse(
                    call: Call<DokterResponse>,
                    response: Response<DokterResponse>
                ) {

                    if (response.isSuccessful){
                        val status=response.body()?.value
                        if (status==202){
                            val results=response.body()?.result
                            allDokterView.onGetAllDokter(results)
                        }
                        else{
                            allDokterView.onFailedGetDokter("Cek Koneksi Internet Anda")
                        }
                    }

                }

            })
    }
    fun getCariDokter(nmDokter:String){
        NetWorkConfig.getServicee()
            .get_CariDokter(nmDokter)
            .enqueue(object :Callback<DokterResponse>{
                override fun onFailure(call: Call<DokterResponse>, t: Throwable) {
                    allDokterView.onFailedGetDokter("Koneksi Internet Bermasalah")
                    Log.d("Error","Error Data")
                }

                override fun onResponse(
                    call: Call<DokterResponse>,
                    response: Response<DokterResponse>
                ) {
                   if (response.isSuccessful){
                       val status=response.body()?.value
                       if (status==202){
                           val results=response.body()?.result
                           allDokterView.onCariDokter(results)
                       }
                       else{
                           allDokterView.onFailedGetDokter("Cek Koneksi Internet Anda")
                       }
                   }
                }

            })

    }
    fun getDetailDokter(usr_id:String){
        NetWorkConfig.getServicee()
            .detail_dokter(usr_id)
            .enqueue(object :Callback<DetailDokterResponse>{
                override fun onFailure(call: Call<DetailDokterResponse>, t: Throwable) {
                    allDokterView.onFailedGetDokter("Koneksi Internet Bermasalah")
                    Log.d("Error","Error Data")
                }

                override fun onResponse(
                    call: Call<DetailDokterResponse>,
                    response: Response<DetailDokterResponse>
                ) {
                    if (response.isSuccessful){
                        val status=response.body()?.value
                        if (status==202){
                            val results=response.body()?.result
                            allDokterView.onDetailDokter(results)
                        }
                        else{
                            allDokterView.onFailedGetDokter("Cek Koneksi Internet Anda")
                        }
                    }
                }

            })
    }
}