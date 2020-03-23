package com.lr.quartetplatform.bean

import com.google.gson.annotations.SerializedName

class ManagerBean {
    var id: String? = ""
    var username: String? = ""
    var nickname: String? = ""
    var mobile: String? = ""
    var avatar: String? = ""
    var score: String? = ""
    var kinds: String? = ""
    var bio: String? = ""

    @SerializedName("prevtime_text")
    var prevtimeText: String? = ""

    @SerializedName("logintime_text")
    var logintimeText: String? = ""

    @SerializedName("jointime_text")
    var jointimeText: String? = ""
}