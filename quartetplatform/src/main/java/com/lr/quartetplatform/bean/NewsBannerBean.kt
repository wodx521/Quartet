package com.lr.quartetplatform.bean

import com.google.gson.annotations.SerializedName

class NewsBannerBean {

    var id: String? = ""

    @SerializedName("post_id")
    var postId: String? = ""
    var title: String? = ""

    @SerializedName("author_name")
    var authorName: String? = ""
    var cover: String? = ""

    @SerializedName("published_at")
    var publishedAt: String? = ""
    var content: String? = ""
}