package com.itrsiam.rsiamuslimat.kartu

import com.itrsiam.rsiamuslimat.api.NetWorkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KartuPresenter(val kartuView: KartuView) {
    fun addkartu(no_rm: String, tanggal_lahir: String, id_login: String) {
        NetWorkConfig.getServicee()
            .addKartu(no_rm, tanggal_lahir, id_login)
            .enqueue(object : Callback<KartuResponse> {
                override fun onResponse(
                    call: Call<KartuResponse>,
                    response: Response<KartuResponse>
                ) {

                    if (response.isSuccessful) {
                        val status = response.body()?.value
                        if (status == 200) {
                            kartuView.onSuccessAdd(response.body()!!.message.toString())

                        } else {
                            kartuView.onFailedAdd(response.body()!!.message.toString())
                        }
                    }
                }

                override fun onFailure(call: Call<KartuResponse>, t: Throwable) {
                    kartuView.onFailureAdd("Gagal")
                }

            })
    }

    fun ekartu(id_login: String) {
        NetWorkConfig.getServicee()
            .rm_kartu(id_login)
            .enqueue(object : Callback<EkartuResponse> {
                override fun onResponse(
                    call: Call<EkartuResponse>,
                    response: Response<EkartuResponse>
                ) {
                    if (response.isSuccessful) {
                        val status = response.body()?.value
                        if (status == 202) {
                            kartuView.onSuccessGet(response.body()!!.result)
                        } else {
                            kartuView.onFailedAdd("Cek Koneksi Internet")
                        }
                    }
                }

                override fun onFailure(call: Call<EkartuResponse>, t: Throwable) {
                    kartuView.onFailureAdd("Gagal")
                }

            })
    }

    fun hapus_kartu(pasienrm: String, id_login: String) {
        NetWorkConfig.getServicee()
            .hapus_kartu(pasienrm, id_login)
            .enqueue(object : Callback<KartuResponse> {
                override fun onResponse(
                    call: Call<KartuResponse>,
                    response: Response<KartuResponse>
                ) {
                    if (response.isSuccessful) {
                        val status = response.body()?.value
                        if (status == 202) {
                            kartuView.onSuccessDel(response.body()!!.message.toString())
                        } else {
                            kartuView.onFailedAdd("Cek Koneksi Internet")
                        }
                    }
                }

                override fun onFailure(call: Call<KartuResponse>, t: Throwable) {
                    kartuView.onFailureAdd("Gagal")
                }

            })

    }
}