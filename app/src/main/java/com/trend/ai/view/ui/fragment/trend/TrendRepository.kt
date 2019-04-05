package com.trend.ai.view.ui.fragment.trend

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.google.gson.JsonElement
import com.trend.ai.core.AppSchedulerProvider
import com.trend.ai.core.BaseViewModel
import com.trend.ai.model.api.Api
import com.trend.ai.model.api.RestData
import com.trend.ai.model.api.request.LoginReq
import com.trend.ai.model.api.response.Content
import com.trend.ai.model.api.response.login.User
import com.trend.ai.model.db.AppDatabase
import com.trend.ai.util.Config
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class TrendRepository @Inject
internal constructor(database: AppDatabase, private val api: Api, private val schedulerProvider: AppSchedulerProvider) :
    BaseViewModel {

    private val disposables = CompositeDisposable()
    private val userMutableLiveData: MutableLiveData<RestData<User>> = MutableLiveData()
    private val contentMutableLiveData: MutableLiveData<ArrayList<Content>> = MutableLiveData()


    fun login2(loginReq: LoginReq): MutableLiveData<RestData<User>> {
        api.login2(loginReq)
            .observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
            .map { data -> data }
            .subscribe(object : Observer<RestData<JsonElement>> {
                override fun onSubscribe(d: Disposable) {
                    disposables.add(d)
                }

                override fun onNext(sources: RestData<JsonElement>) {
                    Log.e("hailpt", " login2 onNext ")
//                    userMutableLiveData.postValue(sources)
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

    fun getContent(categoryId:String): MutableLiveData<ArrayList<Content>> {
        api.getContent(Config.TOKEN, categoryId)
            .observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
            .map { data -> data }
            .subscribe(object : Observer<ArrayList<Content>> {
                override fun onSubscribe(d: Disposable) {
                    disposables.add(d)
                }

                override fun onNext(data: ArrayList<Content>) {
                    contentMutableLiveData.postValue(data)
                    Log.e("hailpt"," getContent onNext ")
                }

                override fun onError(e: Throwable) {
                    Log.e("hailpt"," getContent onError ")
                }

                override fun onComplete() {
                    Log.e("hailpt"," getContent onComplete ")
                }
            })
        return contentMutableLiveData
    }

    override fun onClear() {

    }
}