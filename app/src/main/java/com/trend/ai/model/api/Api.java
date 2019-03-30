package com.trend.ai.model.api;


import com.google.gson.JsonElement;
import com.trend.ai.model.api.request.LoginReq;
import com.trend.ai.model.api.response.category.CategoryRes;
import com.trend.ai.model.api.response.login.Login;
import com.trend.ai.model.data.Articles;
import com.trend.ai.model.data.Sources;
import io.reactivex.Observable;
import retrofit2.http.*;

import java.util.ArrayList;

/**
 * @author ihsan on 11/29/17.
 */

public interface Api {
    @GET("sources")
    Observable<Sources> sources();

    @GET("top-headlines")
    Observable<Articles> topHeadlines(@Query("sources") String sources);

    @GET("trends/place.json")
    Observable<JsonElement> getTrends(@Query("id") String id);

    @GET("user/categories")
    Observable<ArrayList<CategoryRes>> getCategories();

    @GET("user/categories")
    Observable<ArrayList<CategoryRes>> getCategories2(@Header("Authorization") String s);

    @Headers({"Accept: application/json"})
    @POST("auth/login")
    Observable<Login> login(@Body LoginReq loginReq);


    @Headers({"Accept: application/json"})
    @POST("auth/login")
    Observable<RestData<JsonElement>> login2(@Body LoginReq loginReq);

//    @POST("auth/login")
//    Observable<RestData<JsonElement>> sendComment(@Header("Authorization") String s);

}
