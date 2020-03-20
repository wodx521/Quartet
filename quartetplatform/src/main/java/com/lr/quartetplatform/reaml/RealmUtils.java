package com.lr.quartetplatform.reaml;

import android.content.Context;

import com.lr.quartetplatform.bean.DataCache;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmUtils {

    private static Realm realm;

    public static void init(Context context) {
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("cache.realm") //文件名
                .schemaVersion(0) //版本号
                .build();
        Realm.setDefaultConfiguration(config);
        realm = Realm.getInstance(config);
    }

    public static Realm getRealm() {
        return realm;
    }

    public static DataCache queryData(String dataName) {
        return realm.where(DataCache.class).equalTo("cacheName", dataName).findFirst();
    }

    public static void putCache(String dataName, String cacheContent) {
        realm.beginTransaction();
        DataCache dataCache = queryData(dataName);
        if (dataCache == null) {
            dataCache = realm.createObject(DataCache.class);
            dataCache.setCacheName(dataName);
            dataCache.setCacheContent(cacheContent);
        } else {
            if (!dataCache.getCacheContent().equals(cacheContent)) {
                dataCache.setCacheContent(cacheContent);
            }
        }
        realm.commitTransaction();
    }
}
