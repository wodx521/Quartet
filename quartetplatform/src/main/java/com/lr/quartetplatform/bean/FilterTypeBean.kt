package com.lr.quartetplatform.bean

import com.google.gson.annotations.SerializedName

class FilterTypeBean {
    @SerializedName("goods_days")
    var goodsDays: List<CycleTypeBean> = ArrayList()

    var language: List<String> = ArrayList()

    @SerializedName("home_type")
    var homeType: List<String> = ArrayList()


}