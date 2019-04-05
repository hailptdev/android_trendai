package com.trend.ai.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;


import com.trend.ai.core.ViewModelFactory;
import com.trend.ai.di.ViewModelKey;
import com.trend.ai.view.ui.actitivy.login.LoginViewModel;
import com.trend.ai.view.ui.actitivy.menu.MenuViewModel;
import com.trend.ai.view.ui.fragment.main.MainViewModel;
import com.trend.ai.view.ui.fragment.trend.TrendViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * @author ihsan on 12/27/17.
 */

@SuppressWarnings("WeakerAccess")
@Module
public abstract class ViewModelModule {
//
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindsLoginViewModel(LoginViewModel loginViewModel);
//
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindsMainViewModel(MainViewModel sourceViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MenuViewModel.class)
    abstract ViewModel bindsMenuViewModel(MenuViewModel menuViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(TrendViewModel.class)
    abstract ViewModel bindsTrendViewModel(TrendViewModel trendViewModel);
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(DetailViewModel.class)
//    abstract ViewModel bindsDetailViewModel(DetailViewModel sourceViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);

}
