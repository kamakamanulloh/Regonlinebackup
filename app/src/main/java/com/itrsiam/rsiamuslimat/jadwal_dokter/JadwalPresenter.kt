package com.itrsiam.rsiamuslimat.jadwal_dokter

import android.util.Log
import com.itrsiam.rsiamuslimat.api.NetWorkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JadwalPresenter (val jadwalView: JadwalView){
    fun getJadwal(id_poli:String,tanggal:String){
        NetWorkConfig.getServicee()
            .get_jadwal(id_poli,tanggal)
            .enqueue(object : Callback<JadwalResponse>{
                override fun onFailure(call: Call<JadwalResponse>, t: Throwable) {
                    jadwalView.onFailedGetJadwal("Cek Koneksi Internet")
                    Log.d("Error", "Error Data")
                }

                override fun onResponse(
                    call: Call<JadwalResponse>,
                    response: Response<JadwalResponse>
                ) {
                    if (response.isSuccessful){
                        val status=response.body()?.value
                        if (status==202){
                            val results=response.body()?.result
                            jadwalView.onGetJadwal(results)
                        }
                        else{
                            jadwalView.onFailedGetJadwal("Error $status")
                        }
                    }

                }

            })
    }

}