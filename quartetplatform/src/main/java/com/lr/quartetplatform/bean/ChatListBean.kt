package com.lr.quartetplatform.bean

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class ChatListBean(@SerializedName("chat_id")
                   var chatId: String? = "",

                   @SerializedName("bp_pic")
                   var bpPic: String? = "",

                   @SerializedName("bp_name")
                   var bpName: String? = "",

                   @SerializedName("bp_phone")
                   var bpPhone: String? = "",

                   @SerializedName("read_num")
                   var readNum: String? = "",
                   var createtime: String? = "",
                   var content: String? = "") : Parcelable