package com.itrsiam.rsiamuslimat.laboratorium

interface LabView {
    fun onSuccessLab(
        data:List<ResultItem?>?
    )
    fun onFailedLab(
        msg:String?
    )
}