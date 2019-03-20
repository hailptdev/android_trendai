package com.trend.ai.view.ui.fragment.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.trend.ai.model.api.RestData
import com.trend.ai.model.api.request.LoginReq
import com.trend.ai.model.api.response.User
import javax.inject.Inject

class MainViewModel @Inject
constructor(private val repository: MainRepository) : ViewModel() {

    private val loginParam = MutableLiveData<LoginReq>()
    val loginResult: LiveData<RestData<User>>

    init {
        loginResult = Transformations.switchMap(
            loginParam
        ) { param -> repository.login2(loginParam.value!!) }
    }

    fun setLoginParam(loginReq: LoginReq) {
        loginParam.value = loginReq
    }

    override fun onCleared() {
        super.onCleared()
        repository.onClear()
    }
}