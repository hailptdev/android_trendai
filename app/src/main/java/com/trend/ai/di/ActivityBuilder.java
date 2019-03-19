package com.trend.ai.di;



import com.trend.ai.MainActivity;
import com.trend.ai.view.ui.actitivy.login.LoginActivity;
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

}
