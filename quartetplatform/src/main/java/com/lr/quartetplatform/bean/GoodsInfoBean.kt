package com.lr.quartetplatform.bean

import com.google.gson.annotations.SerializedName

class GoodsInfoBean {

    var id: String? = ""
    var name: String? = ""
    var homeimage: String? = ""
    var shufflingimages: String? = ""
    var descimage: String? = ""
    var price: String? = ""
    var standardscontent: String? = ""
    var type: String? = ""
    var langlist: String? = ""
    var langother: String? = ""
    var days: String? = ""
    var website: String? = ""
    var file: String? = ""
    var introduct: String? = ""
    var feature: String? = ""
    var qrcodeimage: String? = ""
    var introducecontent: String? = ""
    var functioncontent: String? = ""
    var principalcontent: String? = ""
    var paycontent: String? = ""
    var salecontent: String? = ""
    var company: String? = ""

    @SerializedName("admin_id")
    var adminId: String? = ""

    @SerializedName("project_id")
    var projectId: String? = ""

    @SerializedName("dm_id")
    var dmId: String? = ""

    @SerializedName("bp_id")
    var bpId: String? = ""

    @SerializedName("page_view")
    var pageView: String? = ""

    @SerializedName("concern_num")
    var concernNum: String? = ""
    var recommond: String? = ""
    var status: String? = ""
    var over: String? = ""
    var uploadtime: String? = ""
    var checktime: String? = ""
    var discount: String? = ""
    var modao: String? = ""
    var label: List<String>? = ArrayList()
    var pcimages: String? = ""
    var appimages: String? = ""
    var feature2: String? = ""
    var feature3: String? = ""
    var feature4: String? = ""

    @SerializedName("now_price")
    var nowPrice: String? = ""

    @SerializedName("langlist_text")
    var langlistText: String? = ""

    @SerializedName("recommond_text")
    var recommondText: String? = ""

    @SerializedName("status_text")
    var statusText: String? = ""

    @SerializedName("uploadtime_text")
    var uploadtimeText: String? = ""

    @SerializedName("checktime_text")
    var checktimeText: String? = ""
}