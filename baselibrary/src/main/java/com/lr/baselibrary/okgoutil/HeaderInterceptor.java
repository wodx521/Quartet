package com.lr.baselibrary.okgoutil;

import com.lr.baselibrary.utils.GsonUtils;
import com.lr.baselibrary.utils.SpUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        int languageSelect = (int) SpUtils.get("languageSelect", -1);
        Request.Builder requestBuilder = request.newBuilder()
                .addHeader("lang", ((languageSelect == -1 ? 0 : languageSelect) + 1) + "")
                .addHeader("token","");
        request = requestBuilder.build();
        return chain.proceed(request);
    }
}
