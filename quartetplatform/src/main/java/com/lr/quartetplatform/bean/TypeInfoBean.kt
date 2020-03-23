package com.lr.quartetplatform.bean

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class TypeInfoBean : Parcelable {
    var color: String? = ""
    var status: String? = ""

    @SerializedName("color_text")
    var colorText: String? = ""

    @SerializedName("status_text")
    var statusText: String? = ""
}