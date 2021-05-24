package com.itrsiam.rsiamuslimat.login

import com.google.gson.annotations.SerializedName

data class User(

    @field:SerializedName("password")
    val password: String? = null,

    @field:SerializedName("nama_depan")
    val namaDepan: String? = null,

    @field:SerializedName("login_cust_id")
    val loginCustId: String? = null,

    @field:SerializedName("nama_belakang")
    val namaBelakang: String? = null,

    @field:SerializedName("login_cust_nama")
    val loginCustNama: String? = null
)