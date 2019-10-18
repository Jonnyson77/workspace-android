package com.example.net;

import com.example.bean.LoginBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ConnectApi {
    @POST("api/login")
    Call<ResponseBody> Login(@Body LoginBean loginBean);
    /*
    * {
  "accountType": 0,
  "code": "string",
  "mobile": "string",
  "password": "string",
  "type": "string"
}*/

}
