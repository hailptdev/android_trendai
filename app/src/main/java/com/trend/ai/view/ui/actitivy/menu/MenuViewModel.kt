package com.trend.ai.view.ui.actitivy.menu

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.trend.ai.model.api.response.Content
import com.trend.ai.model.api.response.category.CategoryRes
import com.trend.ai.model.api.response.login.User
import javax.inject.Inject

class MenuViewModel @Inject
constructor(private val repository: MenuRepository) : ViewModel() {

    private val cateParam = MutableLiveData<Boolean>()
    private val contentParam = MutableLiveData<String>()
    private val userParam = MutableLiveData<Boolean>()
    val categories: LiveData<ArrayList<CategoryRes>>
    val contents: LiveData<ArrayList<Content>>
    val userInfomation: LiveData<User>

    init {
        categories = Transformations.switchMap(cateParam) {
            repository.getCategories() }

        contents = Transformations.switchMap(contentParam) {
            repository.getContent(contentParam.value!!) }

        userInfomation = Transformations.switchMap(userParam) {
            repository.getUserInformation(userParam.value!!) }
    }

    fun setLoginParam(cateParam:Boolean) {
        this.cateParam.value = cateParam
    }

    fun setContentParam(contentParam:String) {
        this.contentParam.value = contentParam
    }

    fun setGetInfomationParam(userParam:Boolean) {
        this.userParam.value = userParam
    }

    override fun onCleared() {
        super.onCleared()
        repository.onClear()
    }
}