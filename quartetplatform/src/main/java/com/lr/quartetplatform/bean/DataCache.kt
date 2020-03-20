package com.lr.quartetplatform.bean

import io.realm.RealmObject

open class DataCache : RealmObject() {
    var cacheName: String? = ""
    var cacheContent: String? = ""

}