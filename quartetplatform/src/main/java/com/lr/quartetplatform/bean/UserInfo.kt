package com.lr.quartetplatform.bean

import com.google.gson.annotations.SerializedName

class UserInfo  {
    var id: String? = ""

    @SerializedName("group_id")
    var groupId: String? = ""
    var username: String? = ""
    var nickname: String? = ""
    var mobile: String? = ""
    var avatar: String? = ""
    var score: String? = ""
    var token: String? = ""

    @SerializedName("user_id")
    var userId: String? = ""
    var createtime: String? = ""
    var expiretime: String? = ""

    @SerializedName("expires_in")
    var expiresIn: String? = ""
}