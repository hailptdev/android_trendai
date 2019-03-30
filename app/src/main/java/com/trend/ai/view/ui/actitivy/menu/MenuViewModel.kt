package com.trend.ai.view.ui.actitivy.menu

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.trend.ai.model.api.response.category.CategoryRes
import javax.inject.Inject

class MenuViewModel @Inject
constructor(private val repository: MenuRepository) : ViewModel() {

    private val cateParam = MutableLiveData<Boolean>()
    val categories: LiveData<ArrayList<CategoryRes>>

    init {
        categories = Transformations.switchMap(cateParam) {
            repository.getCategories() }
    }

    fun setLoginParam(cateParam:Boolean) {
        this.cateParam.value = cateParam
    }

    override fun onCleared() {
        super.onCleared()
        repository.onClear()
    }
}