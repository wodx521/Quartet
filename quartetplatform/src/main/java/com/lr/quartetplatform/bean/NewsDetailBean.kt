package com.lr.quartetplatform.bean

import com.google.gson.annotations.SerializedName

class NewsDetailBean {
    var id: String? = ""
    var title: String? = ""
    @SerializedName("author_name")
    var authorName: String? = ""
    @SerializedName("published_at")
    var publishedAt: String? = ""
    var content: String? = ""
    var cover: String? = ""
}