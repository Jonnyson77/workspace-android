package com.example.net;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ConnectApi {
    @GET("https://www.baidu.com")
    Call<ResponseBody> getUserInfo();

}
