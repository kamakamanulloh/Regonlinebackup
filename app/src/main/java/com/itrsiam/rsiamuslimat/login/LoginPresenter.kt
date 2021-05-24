package com.itrsiam.rsiamuslimat.login

import com.itrsiam.rsiamuslimat.api.NetWorkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(val loginView: LoginView) {
    fun login(username : String, password : String){
        NetWorkConfig.getServicee()
            .login(username,password)
            .enqueue(object : Callback<LoginResponse>{
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    loginView.onFailure(t.localizedMessage)
                }
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>) {
                    if (response.body()?.value == 202){
                        loginView.onSuccessLogin( response.body()?.message,
                            response.body()?.id,response.body()?.username,response.body()?.no_hp)
                    } else {
                        loginView.onFailedLogin( response.body()?.message)
                    }
                }
            })
    }

    fun profil(user_id:String){
        NetWorkConfig.getServicee()
            .profil(user_id)
            .enqueue(object :Callback<LoginResponse>{
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    loginView.onFailure(t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    val value= response.body()?.value
                   if (response.isSuccessful){
                       if (value==202){
                           loginView.onProfil(response.body()!!.id,
                           response.body()!!.username, response.body()!!.no_hp,
                           response.body()!!.nama_depan, response.body()!!.nama_belakang,
                           response.body()!!.password)
                       }
                   }
                }

            })
    }

    fun edit_profil(user_id:String,username:String?,nama_depan:String?,nama_belakang:String?,
                   no_hp:String?){
        NetWorkConfig.getServicee()
            .update_profil(user_id,username,nama_depan,nama_belakang,no_hp)
            .enqueue(object :Callback<LoginResponse>{
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    loginView.onErrorUpdate(t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    val value= response.body()?.value
                    if (response.isSuccessful){
                        if (value==202){
                            loginView.onSuccessUpdate(response.body()!!.message!!)
                        }
                        else{
                            loginView.onErrorUpdate(response.body()!!.message!!)
                        }
                    }
                }

            })


    }

    fun edit_password(password: String,
                    user_id:String?){
        NetWorkConfig.getServicee()
            .update_password(password,user_id)
            .enqueue(object :Callback<LoginResponse>{
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    loginView.onErrorUpdate(t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    val value= response.body()?.value
                    if (response.isSuccessful){
                        if (value==202){
                            loginView.onSuccessUpdate(response.body()!!.message!!)
                        }
                        else{
                            loginView.onErrorUpdate(response.body()!!.message!!)
                        }
                    }
                }

            })


    }


}

