package com.lr.quartetplatform.bean

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class SimiliarBean(var id: String? = "",
                   var name: String? = "",
                   var homeimage: String? = "",
                   var introduct: String? = "",

                   @SerializedName("langlist_text")
                   var langlistText: String? = "",

                   @SerializedName("recommond_text")
                   var recommondText: String? = "",

                   @SerializedName("status_text")
                   var statusText: String? = "",

                   @SerializedName("uploadtime_text")
                   var uploadtimeText: String? = "",

                   @SerializedName("checktime_text")
                   var checktimeText: String? = "") : Parcelable