package com.itrsiam.rsiamuslimat.diagnosa



interface DiagnosaView {
    fun onSuccessDiagnosa(
        data:List<ResultItem?>?
    )
    fun onFailedDiagnosa(
        msg:String?
    )
}