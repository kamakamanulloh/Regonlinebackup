package com.itrsiam.rsiamuslimat.pasien.bpjs

import com.itrsiam.rsiamuslimat.api.NetWorkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CekKepesertaanPresenter(val cekKepesertaanView: CekKepesertaanView) {
    fun cekPeserta(param:String,tgl:String){
        NetWorkConfig.getServicee()
            .get_Kepesertaan(param, tgl)
            .enqueue(object : Callback<CekKepesertaanResponse>{
                override fun onResponse(
                    call: Call<CekKepesertaanResponse>,
                    response: Response<CekKepesertaanResponse>
                ) {  val respons=response.body()
                    if (response.body()?.metaData!!.code=="200"){

                        cekKepesertaanView.onSuccessCek(respons!!.metaData?.message,
                        respons?.response?.peserta?.nama, respons?.response?.peserta?.statusPeserta?.keterangan?.toString(),
                            respons?.response?.peserta?.provUmum?.nmProvider  ,respons?.response?.peserta?.jenisPeserta?.keterangan
                        )

                    }
                    else{
                        cekKepesertaanView.onFailedCek(respons!!.metaData?.message)
                    }

                }

                override fun onFailure(call: Call<CekKepesertaanResponse>, t: Throwable) {
                    cekKepesertaanView.onFailedCek("Gagal")
                }

            })
    }
}