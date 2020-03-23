package com.lr.quartetplatform.bean

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class GoodDetailBean(
        var id: String? = "",
        var name: String? = "",
        var homeimage: String? = "",
        var shufflingimages: String? = "",
        var descimage: String? = "",
        var price: String? = "",
        var standardscontent: String? = "",
        var type: String? = "",
        var langlist: String? = "",
        var langother: String? = "",
        var days: String? = "",
        var website: String? = "",
        var file: String? = "",
        var introduct: String? = "",
        var feature: String? = "",
        var qrcodeimage: String? = "",
        var introducecontent: String? = "",
        var functioncontent: String? = "",
        var principalcontent: String? = "",
        var paycontent: String? = "",
        var salecontent: String? = "",
        var company: String? = "",

        @SerializedName("admin_id")
        var adminId: String? = "",

        @SerializedName("project_id")
        var projectId: String? = "",

        @SerializedName("dm_id")
        var dmId: String? = "",

        @SerializedName("bp_id")
        var bpId: String? = "",

        @SerializedName("page_view")
        var pageView: String? = "",

        @SerializedName("concern_num")
        var concernNum: String? = "",
        var recommond: String? = "",
        var status: String? = "",
        var over: String? = "",
        var uploadtime: Long? = 0,
        var checktime: Long? = 0,
        var discount: String? = "",
        var modao: String? = "",
        var label: ArrayList<String>? = ArrayList(),
        var pcimages: String? = "",
        var appimages: String? = "",
        var feature2: String? = "",
        var feature3: String? = "",
        var feature4: String? = "",
        var now_price: String? = "",
        var typeInfo: TypeInfoBean? = TypeInfoBean(),
        var concern: String? = "",
        var similiar: ArrayList<SimiliarBean>? = ArrayList(),

        @SerializedName("feature_detail")
        var featureDetail: ArrayList<FeatureDetailBean>? = ArrayList(),

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