package com.itrsiam.rsiamuslimat.kartu



interface KartuView {
    fun onSuccessAdd(msg: String?)
    fun onSuccessDel(msg: String?)
    fun onFailedAdd (msg : String?)
    fun onSuccessGetList(data: List<EkartuResultItem?>?)



    fun onFailureAdd(msg: String?)
}