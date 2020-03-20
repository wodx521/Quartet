package com.lr.quartetplatform;

import com.lr.baselibrary.utils.SpUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderIntercep implements Interceptor {

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        Request request1 = request.newBuilder()
                .addHeader("token", (String) SpUtils.get("token",""))
                .build();
        return chain.proceed(request1);
    }
}
