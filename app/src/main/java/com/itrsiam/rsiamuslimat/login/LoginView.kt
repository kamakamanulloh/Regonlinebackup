package com.itrsiam.rsiamuslimat.login


interface LoginView {
    fun onSuccessLogin (
        msg: String?,
        id: String?,
        username: String?,
        nohp: String?
    )
    fun onProfil (

        id: String?,
        username: String?,
        nohp: String?,
        nama_depan:String?,
        nama_belakang:String?,
        password:String?
    )
    fun onFailedLogin (msg : String?)

    fun onFailure(msg: String?)


    fun onSuccessUpdate(msg : String)
    fun onErrorUpdate(msg : String)
}
