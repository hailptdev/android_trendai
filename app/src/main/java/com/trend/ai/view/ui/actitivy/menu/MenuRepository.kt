package com.trend.ai.view.ui.actitivy.menu

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.trend.ai.core.AppSchedulerProvider
import com.trend.ai.core.BaseViewModel
import com.trend.ai.model.api.Api
import com.trend.ai.model.api.response.Content
import com.trend.ai.model.api.response.Media
import com.trend.ai.model.api.response.Topic
import com.trend.ai.model.api.response.category.CategoryRes
import com.trend.ai.model.api.response.category.Country
import com.trend.ai.model.api.response.login.User
import com.trend.ai.model.db.AppDatabase
import com.trend.ai.util.Config
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MenuRepository @Inject
internal constructor(database: AppDatabase, private val api: Api, private val schedulerProvider: AppSchedulerProvider) :
    BaseViewModel {

    private val disposables = CompositeDisposable()
    private val cateMutableLiveData: MutableLiveData<ArrayList<CategoryRes>> = MutableLiveData()
    private val topicMutableLiveData: MutableLiveData<ArrayList<Topic>> = MutableLiveData()
    private val contentMutableLiveData: MutableLiveData<ArrayList<Content>> = MutableLiveData()
    private val userMutableLiveData: MutableLiveData<User> = MutableLiveData()
    private val mediacMutableLiveData: MutableLiveData<ArrayList<Media>> = MutableLiveData()

    private val topiccMutableLiveData: MutableLiveData<ArrayList<Topic>> = MutableLiveData()


    private val countriesMutableLiveData: MutableLiveData<ArrayList<Country>> = MutableLiveData()

    fun getCategories(): MutableLiveData<ArrayList<CategoryRes>> {
        api.getCategories2(Config.TOKEN)
            .observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
            .map { data -> data }
            .subscribe(object : Observer<ArrayList<CategoryRes>> {
                override fun onSubscribe(d: Disposable) {
                    disposables.add(d)
                }

                override fun onNext(data: ArrayList<CategoryRes>) {
                    cateMutableLiveData.postValue(data)
                }

                override fun onError(e: Throwable) {

                }

                override fun onComplete() {

                }
            })
        return cateMutableLiveData
    }

    // Get Topic / Content / Media / Influencer

    /* Topic */

    /* Content */

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


    fun getUserInformation(isGet:Boolean): MutableLiveData<User> {
        api.getUserInfomation(Config.TOKEN)
            .observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
            .map { data -> data }
            .subscribe(object : Observer<User> {
                override fun onSubscribe(d: Disposable) {
                    disposables.add(d)
                }

                override fun onNext(data: User) {
                    userMutableLiveData.postValue(data)
                    Log.e("hailpt"," getUserInformation onNext ")
                }

                override fun onError(e: Throwable) {
                    Log.e("hailpt"," getUserInformation onError ")
                }

                override fun onComplete() {
                    Log.e("hailpt"," getUserInformation onComplete ")
                }
            })
        return userMutableLiveData
    }

    fun getCountries(isGet:Boolean): MutableLiveData<ArrayList<Country>> {
        api.getCountries(Config.TOKEN)
            .observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
            .map { data -> data }
            .subscribe(object : Observer<ArrayList<Country>> {
                override fun onSubscribe(d: Disposable) {
                    disposables.add(d)
                }

                override fun onNext(data: ArrayList<Country>) {
                    countriesMutableLiveData.postValue(data)
                    Log.e("hailpt"," getCountries onNext ")
                }

                override fun onError(e: Throwable) {
                    Log.e("hailpt"," getCountries onError ")
                }

                override fun onComplete() {
                    Log.e("hailpt"," getCountries onComplete ")
                }
            })
        return countriesMutableLiveData
    }


    fun getTrendsByLocation(isGet:Boolean): MutableLiveData<ArrayList<Topic>> {
        api.getTrendsByLocation(Config.TOKEN,1,1,23424740)
            .observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
            .map { data -> data }
            .subscribe(object : Observer<ArrayList<Topic>> {
                override fun onSubscribe(d: Disposable) {
                    disposables.add(d)
                }

                override fun onNext(data: ArrayList<Topic>) {
                    topiccMutableLiveData.postValue(data)
                    Log.e("hailpt"," getCountries onNext ")
                }

                override fun onError(e: Throwable) {
                    Log.e("hailpt"," getCountries onError ")
                }

                override fun onComplete() {
                    Log.e("hailpt"," getCountries onComplete ")
                }
            })
        return topiccMutableLiveData
    }



    override fun onClear() {
        disposables.clear()
    }
}