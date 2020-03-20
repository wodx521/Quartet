package com.lr.quartetplatform.bean

import com.google.gson.annotations.SerializedName

class CustomResult {
    var type: String? = ""
    var language: String? = ""
    var timelimit: String? = ""
    var demandcontent: String? = ""
    var homeimage: String? = ""
    var mobile: String? = ""
    @SerializedName("user_id")
    var userId: String? = ""
    var username: String? = ""
    var createtime: String? = ""
    var id: String? = ""
    @SerializedName("status_text")
    var statusText: String? = ""
    @SerializedName("checktime_text")
    var checktimeText: String? = ""
}