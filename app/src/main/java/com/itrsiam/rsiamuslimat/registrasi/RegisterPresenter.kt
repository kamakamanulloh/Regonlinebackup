package com.itrsiam.rsiamuslimat.registrasi

import com.itrsiam.rsiamuslimat.api.NetWorkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterPresenter (val registerView: RegisterView){

    fun register( username:String?,nama_depan:String?,nama_belakang:String?,
                  password:String?,no_hp:String?){
        NetWorkConfig.getServicee()
            .register(username,nama_depan,nama_belakang,password,no_hp)
            .enqueue(object : Callback<RegisterResults> {
                override fun onFailure(call: Call<RegisterResults>, t: Throwable) {
                    registerView.onFailedRegister("Cek Koneksi Internet")
                }

                override fun onResponse(
                    call: Call<RegisterResults>,
                    response: Response<RegisterResults>
                ) {
                    if ( response.body()?.value == 200){
                        registerView.onSuccessRegister(response.body()?.message)
                    } else{
                        registerView.onFailedRegister(response.body()?.message)
                    }
                }

            })
    }
}
