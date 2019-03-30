package com.trend.ai.di.module;



import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;
import com.trend.ai.BuildConfig;
import com.trend.ai.model.api.Api;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Singleton;

/**
 * @author ihsan on 2/28/18.
 */
@Module
public class NetworkModule {
    public String BASE_URL = "http://myelcom.elcom.com.vn:8000/api/";
    public String BASE_URL2 = "https://trendai-bb810.appspot.com/v1/";
    @Provides
    @Singleton
    LoggingInterceptor provideInterceptor() {
        return new LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .setLevel(Level.NONE)
                .log(Platform.INFO)
                .request("Request")
                .response("Response")
//                .addHeader("Authorization", "Bearer "+ Config.INSTANCE.getTOKEN())
//                .addQueryParam("apiKey", "")
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttp(LoggingInterceptor interceptor) {
        return new OkHttpClient.Builder()
//                .addNetworkInterceptor(interceptor)
                .build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL2)
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    Api provideApi(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }
}
