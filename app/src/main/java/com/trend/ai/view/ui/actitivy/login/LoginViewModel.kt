package com.trend.ai.view.ui.actitivy.login

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.google.gson.JsonElement
import com.trend.ai.model.api.request.LoginReq
import com.trend.ai.model.api.response.category.Country
import com.trend.ai.model.api.response.login.Login
import javax.inject.Inject

class LoginViewModel @Inject
constructor(private val repository: LoginRepository) : ViewModel() {

    private val loginParam = MutableLiveData<LoginReq>()
    private val trendsParam = MutableLiveData<String>()
    private val countriesParam = MutableLiveData<Boolean>()


    val loginResult: LiveData<Login>
    val trendsResult: LiveData<JsonElement>

    val countriesResult: LiveData<List<Country>>

    init {
        loginResult = Transformations.switchMap(loginParam) {
            repository.login2(loginParam.value!!) }

        trendsResult = Transformations.switchMap(
            trendsParam
        ) { repository.getTrends(trendsParam.value!!) }

        countriesResult = Transformations.switchMap(
            countriesParam
        ) { repository.getCountries(countriesParam.value!!) }

    }

    fun setLoginParam(loginReq: LoginReq) {
        loginParam.value = loginReq
    }

    fun setTrendsParam(id: String) {
        trendsParam.value = id
    }

    fun setCountries(param: Boolean) {
        countriesParam.value = param
    }

    override fun onCleared() {
        super.onCleared()
        repository.onClear()
    }
}