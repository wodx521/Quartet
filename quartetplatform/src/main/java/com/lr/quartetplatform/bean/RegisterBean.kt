package com.lr.quartetplatform.bean

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

class RegisterBean{
    @SerializedName("userinfo")
    var userInfo:UserInfo?= UserInfo()
}