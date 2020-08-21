package com.zyong.ocr.core.util;

import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HttpUtils {
    private static OkHttpClient okHttpClient = null;
    public final static String USER_AGENT = "License Data Push Agent";

    static{
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(20, TimeUnit.SECONDS);
        okHttpClient = builder.build();
    }

    public static ResponseBody get(String url) throws IOException{
        return get(url, null);
    }

    public static ResponseBody get(String url, Headers.Builder headersBuilder) throws IOException{
        Request.Builder builder = new Request.Builder().url(url);
        if(headersBuilder == null){
            headersBuilder = new Headers.Builder().add("user-agent", USER_AGENT);
        }
        Request request = builder.headers(headersBuilder.build()).build();
        Call call = okHttpClient.newCall(request);
        return call.execute().body();
    }

    public static ResponseBody post(String url, RequestBody requestBody, Headers.Builder headersBuilder) throws IOException{
        Request.Builder builder = new Request.Builder().url(url);
        if(headersBuilder != null) builder.headers(headersBuilder.build());
        if(requestBody != null) builder.post(requestBody);

        Request request = builder.build();
        Call call = okHttpClient.newCall(request);
        return call.execute().body();
    }

    public static ResponseBody post(String url, RequestBody requestBody) throws IOException{
        return post(url, requestBody, null);
    }

}
