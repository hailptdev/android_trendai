package com.trend.ai.di;


import com.trend.ai.MainActivity;
import com.trend.ai.view.ui.actitivy.login.ChooseLocationActivity;
import com.trend.ai.view.ui.actitivy.login.LoginActivity;
import com.trend.ai.view.ui.actitivy.menu.MenuLeftActivity;
import com.trend.ai.view.ui.actitivy.menu.MenuNormalActivity;
import com.trend.ai.view.ui.fragment.main.MainFragment;
import com.trend.ai.view.ui.fragment.trend.*;
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

    @ContributesAndroidInjector
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector
    abstract MainFragment bindMainFragment();

    @ContributesAndroidInjector
    abstract MenuNormalActivity bindMenuNormalActivity();

    @ContributesAndroidInjector
    abstract MenuLeftActivity bindMenuLeftActivity();

    // Fragment

    @ContributesAndroidInjector
    abstract TopicFragment bindTopicFragment();

    @ContributesAndroidInjector
    abstract InfluencerFragment bindInfluencerFragment();

    @ContributesAndroidInjector
    abstract ContentFragment bindContentFragment();

    @ContributesAndroidInjector
    abstract VideosFragment bindMediaFragment();

    @ContributesAndroidInjector
    abstract PhotosFragment bindPhotosFragment();

    @ContributesAndroidInjector
    abstract ChooseLocationActivity bindChooseLocationActivity();

}
