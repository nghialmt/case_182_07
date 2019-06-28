package com.example.faceshop;

import com.example.faceshop.Retrofit.IAPI;
import com.example.faceshop.Retrofit.RetrofitClient;

public class ApiUtils {
    public static final String BASE_URL = "http://192.168.99.101:3000/";
    public static final String key = "20101998";

    public static IAPI getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(IAPI.class);
    }
}
