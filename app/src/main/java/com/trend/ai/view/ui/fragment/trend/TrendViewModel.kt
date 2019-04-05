package com.trend.ai.view.ui.fragment.trend

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.trend.ai.model.api.RestData
import com.trend.ai.model.api.request.LoginReq
import com.trend.ai.model.api.response.Content
import com.trend.ai.model.api.response.login.User
import javax.inject.Inject

class TrendViewModel @Inject
constructor(private val repository: TrendRepository) : ViewModel() {

    private val contentParam = MutableLiveData<String>()
    val contents: LiveData<ArrayList<Content>>

    init {
        contents = Transformations.switchMap(contentParam) {
            repository.getContent(contentParam.value!!) }
    }

    fun setContentParam(contentParam:String) {
        this.contentParam.value = contentParam
    }

    override fun onCleared() {
        super.onCleared()
        repository.onClear()
    }
}