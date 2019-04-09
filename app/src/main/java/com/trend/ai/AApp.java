package com.trend.ai;


import android.util.Log;
import com.trend.ai.di.DaggerAppComponent;
import com.trend.ai.util.FontsOverride;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * @author ihsan on 11/29/17.
 */

public class AApp extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/HelveticaWorld-Regular.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/HelveticaWorld-Regular.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "fonts/HelveticaWorld-Regular.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/HelveticaWorld-Regular.ttf");

        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))//enable logging when app is in debug mode
                .twitterAuthConfig(new TwitterAuthConfig("pYpazXkV7b5jZ9wqEdiHxWq9n", "vNCr3oZ2numzzp7wRp3qe7VzoUmRI0YsuMPWz4MEQMrIjgmYLn"))//pass the created app Consumer KEY and Secret also called API Key and Secret
                .debug(true)//enable debug mode
                .build();

        //finally initialize twitter with created configs
        Twitter.initialize(config);
    }
}
