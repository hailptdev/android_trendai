package com.trend.ai.di;

import com.example.arc.view.ui.DetailActivity;
import com.example.arc.view.ui.MainActivity;
import com.example.arc.view.ui.SourcesActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author ihsan on 12/2/17.
 */

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract SourcesActivity bindSourceActivity();

    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract DetailActivity bindDetailActivity();

}
