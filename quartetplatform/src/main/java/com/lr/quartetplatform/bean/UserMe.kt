package com.lr.quartetplatform.bean

import com.google.gson.annotations.SerializedName

class UserMe {
    var id: String? = ""
    var nickname: String? = ""
    var mobile: String? = ""
    var bio: String? = ""
    var avatar: String? = ""
    var invitaioncode: String? = ""
    var qrcode: String? = ""
    @SerializedName("bus_img")
    var busImg: String? = ""
}