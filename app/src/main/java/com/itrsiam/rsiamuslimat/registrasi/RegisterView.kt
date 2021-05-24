package com.itrsiam.rsiamuslimat.registrasi

interface RegisterView {
    fun onSuccessRegister(msg:String?)
    fun onFailedRegister(msg: String?)
}