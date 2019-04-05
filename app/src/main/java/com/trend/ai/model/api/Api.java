package com.trend.ai.model.api;


import com.google.gson.JsonElement;
import com.trend.ai.model.api.request.LoginReq;
import com.trend.ai.model.api.response.Content;
import com.trend.ai.model.api.response.Influencer;
import com.trend.ai.model.api.response.Topic;
import com.trend.ai.model.api.response.category.CategoryRes;
import com.trend.ai.model.api.response.login.Login;
import com.trend.ai.model.api.response.login.User;
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
    Observable<ArrayList<CategoryRes>> getCategories2(@Header("Authorization") String s);



    @Headers({"Accept: application/json"})
    @POST("auth/login")
    Observable<Login> login(@Body LoginReq loginReq);


    @Headers({"Accept: application/json"})
    @POST("auth/login")
    Observable<RestData<JsonElement>> login2(@Body LoginReq loginReq);

    @GET("user") // Topics
    Observable<User> getUserInfomation(@Header("Authorization") String s);




    /* TRENDING */

    // From Category
    @GET("trends/interest") // Topics
    Observable<ArrayList<Topic>> getTopics(@Header("Authorization") String s, @Query("category_id") String  category_id);

    @GET("trends/influencers/interest") // Topics
    Observable<ArrayList<Influencer>> getInfluencers(@Header("Authorization") String s, @Query("category_id") String  category_id);


    @GET("trends/contents/interest") // Contents
    Observable<ArrayList<Content>> getContent(@Header("Authorization") String s,@Query("category_id") String  category_id);

    @GET("trends/medias/interest") // Medias
    Observable<ArrayList<Content>> getMedias(@Header("Authorization") String s,@Query("category_id") String  category_id,@Query("filter") String filter);

    // From Location

}
