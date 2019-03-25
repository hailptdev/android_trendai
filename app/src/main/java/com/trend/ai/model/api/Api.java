package com.trend.ai.model.api;


import com.google.gson.JsonElement;
import com.trend.ai.model.api.request.LoginReq;
import com.trend.ai.model.api.response.User;
import com.trend.ai.model.data.Articles;
import com.trend.ai.model.data.Sources;
import io.reactivex.Observable;
import retrofit2.http.*;

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


    @POST("auth/login")
    Observable<RestData<JsonElement>> login(@Body LoginReq loginReq);

//    @POST("auth/login")
//    Observable<RestData<JsonElement>> sendComment(@Header("Authorization") String s);

}
