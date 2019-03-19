package com.trend.ai.di;



import com.trend.ai.MainActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author ihsan on 12/2/17.
 */

@Module
abstract class ActivityBuilder {

//    @ContributesAndroidInjector
//    abstract SourcesActivity bindSourceActivity();

    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

//    @ContributesAndroidInjector
//    abstract DetailActivity bindDetailActivity();

}
