package com.trend.ai.view.ui.fragment.main

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.trend.ai.core.AppSchedulerProvider
import com.trend.ai.core.BaseViewModel
import com.trend.ai.model.api.Api
import com.trend.ai.model.api.RestData
import com.trend.ai.model.api.request.LoginReq
import com.trend.ai.model.api.response.User
import com.trend.ai.model.db.AppDatabase
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MainRepository @Inject
internal constructor(database: AppDatabase, private val api: Api, private val schedulerProvider: AppSchedulerProvider) :
    BaseViewModel {

    private val disposables = CompositeDisposable()
    private val userMutableLiveData: MutableLiveData<RestData<User>> = MutableLiveData()

    fun login2(loginReq: LoginReq): MutableLiveData<RestData<User>> {
        api.login(loginReq)
            .observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
            .map { data -> data }
            .subscribe(object : Observer<RestData<User>> {
                override fun onSubscribe(d: Disposable) {
                    disposables.add(d)
                }

                override fun onNext(sources: RestData<User>) {
                    Log.e("hailpt", " login2 onNext ")
                    userMutableLiveData.postValue(sources)
                }

                override fun onError(e: Throwable) {
                    Log.e("hailpt", " login2 onError " + e.message)
                }

                override fun onComplete() {
                    Log.e("hailpt", " login2 onComplete")
                }
            })
        return userMutableLiveData
    }

    override fun onClear() {

    }
}