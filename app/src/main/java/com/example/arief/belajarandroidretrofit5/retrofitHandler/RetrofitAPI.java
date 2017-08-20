package com.example.arief.belajarandroidretrofit5.retrofitHandler;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Map;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Arief on 8/19/2017.
 */

public interface RetrofitAPI {

    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponseBody> insertAPI(@FieldMap Map<String,Object> map);


    @GET("select.php")
    Call<JsonArray> getAll();

    @GET("findById.php")
    Call<JsonObject> findById(@Query("id")int id);

}
