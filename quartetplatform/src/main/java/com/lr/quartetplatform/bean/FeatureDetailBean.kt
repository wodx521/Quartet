package com.lr.quartetplatform.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class FeatureDetailBean(var title: String? = "",
                        var desc: String? = "") : Parcelable