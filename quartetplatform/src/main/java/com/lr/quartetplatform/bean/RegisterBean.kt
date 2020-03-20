package com.lr.quartetplatform.bean

import com.google.gson.annotations.SerializedName

class RegisterBean {
    @SerializedName("userinfo")
    var userInfo: UserInfo? = UserInfo()
}