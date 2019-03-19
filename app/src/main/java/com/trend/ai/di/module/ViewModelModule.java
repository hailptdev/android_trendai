package com.trend.ai.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;


import com.trend.ai.core.ViewModelFactory;
import com.trend.ai.di.ViewModelKey;
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
//    @Binds
//    @IntoMap
//    @ViewModelKey(MainViewModel.class)
//    abstract ViewModel bindsMainViewModel(MainViewModel mainViewModel);
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(SourceViewModel.class)
//    abstract ViewModel bindsSourceViewModel(SourceViewModel sourceViewModel);
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(DetailViewModel.class)
//    abstract ViewModel bindsDetailViewModel(DetailViewModel sourceViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);

}
