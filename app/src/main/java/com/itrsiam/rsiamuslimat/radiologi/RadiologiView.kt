package com.itrsiam.rsiamuslimat.radiologi

interface RadiologiView {

    fun onSuccessRadiologi(
       data:List<ResultItem?>?
    )
    fun onFailedRadiologi(
        msg: String?
    )
}