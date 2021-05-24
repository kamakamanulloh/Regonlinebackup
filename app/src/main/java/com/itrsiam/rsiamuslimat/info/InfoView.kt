package com.itrsiam.rsiamuslimat.info

interface InfoView {
    fun onSuccessInfo (data: List<ResultItemInfo?>?)
    fun onFailedInfo(msg:String)
}