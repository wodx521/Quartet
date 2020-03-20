package com.lr.baselibrary.okgoutil;

import android.app.Application;

import com.lr.baselibrary.BuildConfig;
import com.lr.baselibrary.utils.UiTools;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

public class OkGoUtils {
    private static final int TIMEOUT_SECOND = 10000;
    public static OkHttpClient build;
    private static String mBaseUrl;
    private static SPCookieStore spCookieStore;

    public static void initOkGo(Application application, String baseUrl) {
        if (UiTools.noEmpty(baseUrl)) {
            mBaseUrl = baseUrl;
        }
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        if (BuildConfig.DEBUG) {
            loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        }
        loggingInterceptor.setColorLevel(Level.WARNING);
        builder.addInterceptor(loggingInterceptor)
                .addInterceptor(new HeaderInterceptor())
                .readTimeout(TIMEOUT_SECOND, TimeUnit.MILLISECONDS)
                .writeTimeout(TIMEOUT_SECOND, TimeUnit.MILLISECONDS)
                .connectTimeout(TIMEOUT_SECOND, TimeUnit.MILLISECONDS);
        //cookie的缓存设置
        spCookieStore = new SPCookieStore(application);
        builder.cookieJar(new CookieJarImpl(spCookieStore));
        build = builder.build();
        OkGo.getInstance()
                .setOkHttpClient(build)
                .setRetryCount(0)
                .init(application);
    }

    public static void initOkGo(Application application, Interceptor interceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        if (BuildConfig.DEBUG) {
            loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        }
        loggingInterceptor.setColorLevel(Level.WARNING);
        builder.addInterceptor(loggingInterceptor)
                .addInterceptor(interceptor)
                .readTimeout(TIMEOUT_SECOND, TimeUnit.MILLISECONDS)
                .writeTimeout(TIMEOUT_SECOND, TimeUnit.MILLISECONDS)
                .connectTimeout(TIMEOUT_SECOND, TimeUnit.MILLISECONDS);
        //cookie的缓存设置
        spCookieStore = new SPCookieStore(application);
        builder.cookieJar(new CookieJarImpl(spCookieStore));
        build = builder.build();
        OkGo.getInstance()
                .setOkHttpClient(build)
                .setRetryCount(0)
                .init(application);
    }

    public static void getRequest(String url, Object tag, Object httpParams, StringCallback stringCallback) {
        GetRequest<String> stringGetRequest;
        if (UiTools.noEmpty(mBaseUrl)) {
            stringGetRequest = OkGo.<String>get(mBaseUrl)
                    .tag(tag);
        } else {
            stringGetRequest = OkGo.<String>get(url)
                    .tag(tag);
        }
        if (httpParams == null) {
            stringGetRequest.execute(stringCallback);
        } else {
            if (httpParams instanceof HttpParams) {
                stringGetRequest.params((HttpParams) httpParams)
                        .execute(stringCallback);
            }
            if (httpParams instanceof Map) {
                stringGetRequest.params((Map<String, String>) httpParams)
                        .execute(stringCallback);
            }
        }
    }

    public static void postRequest(String url, Object tag, Object objParams, StringCallback stringCallback) {
        PostRequest<String> stringPostRequest;
        if (UiTools.noEmpty(mBaseUrl)) {
            stringPostRequest = OkGo.<String>post(mBaseUrl).tag(tag);
        } else {
            stringPostRequest = OkGo.<String>post(url).tag(tag);
        }
        if (objParams != null) {
            if (objParams instanceof String) {
                stringPostRequest
                        .upJson((String) objParams)
                        .execute(stringCallback);
            }
            if (objParams instanceof HttpParams) {
                stringPostRequest
                        .params((HttpParams) objParams)
                        .execute(stringCallback);
            }
            if (objParams instanceof Map) {
                stringPostRequest
                        .isSpliceUrl(true)
                        .params((Map<String, String>) objParams)
                        .execute(stringCallback);
            }
        } else {
            stringPostRequest
                    .execute(stringCallback);
        }
    }

    public static void cancelConnect(String tag) {
        OkGo.getInstance().cancelTag(tag);
    }

    public static void cancelAll() {
        OkGo.getInstance().cancelAll();
    }

    public static SPCookieStore getSpCookieStore() {
        return spCookieStore;
    }
}
