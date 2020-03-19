package com.lr.quartetplatform.bean

import com.google.gson.annotations.SerializedName

class HomeInfoBean(@SerializedName("home_type")
                   var homeType: List<HomeTypeBean>? = ArrayList(),

                   @SerializedName("home_recommend")
                   var homeRecommend: List<HomeTypeBean>? = ArrayList(),

                   @SerializedName("home_nav")
                   var homeNav: List<String>? = ArrayList(),

                   var attention: String? = "") {

}