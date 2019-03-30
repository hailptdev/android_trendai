package com.trend.ai.core.base;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import com.trend.ai.util.ProgressDialogUtils;
import dagger.android.support.DaggerAppCompatActivity;

import javax.inject.Inject;

/**
 * @author ihsan on 11/29/17.
 */

public abstract class BaseActivity<M extends ViewModel, B extends ViewDataBinding> extends DaggerAppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @SuppressWarnings("unchecked")
    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewDataBinding binding = DataBindingUtil.setContentView(this, getLayoutResId());
        ViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModel());
        onCreate(savedInstanceState, (M) viewModel, (B) binding);
    }

    protected abstract Class<M> getViewModel();

    protected abstract void onCreate(Bundle instance, M viewModel, B binding);

    protected abstract
    @LayoutRes
    int getLayoutResId();

    public void showProgessDialog() {
        ProgressDialogUtils.showProgressDialog(this, 0, 0);
    }

    public void dismisProgressDialog() {
        ProgressDialogUtils.dismissProgressDialog();
    }

}
