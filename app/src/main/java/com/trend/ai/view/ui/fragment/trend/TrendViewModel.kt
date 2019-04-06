package com.trend.ai.view.ui.fragment.trend

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.trend.ai.model.api.request.MediaReq
import com.trend.ai.model.api.response.Content
import com.trend.ai.model.api.response.Influencer
import com.trend.ai.model.api.response.Media
import javax.inject.Inject

class TrendViewModel @Inject
constructor(private val repository: TrendRepository) : ViewModel() {

    private val contentParam = MutableLiveData<String>()
    val contents: LiveData<ArrayList<Content>>

    private val peopleParam = MutableLiveData<String>()
    val people: LiveData<ArrayList<Influencer>>

    private val mediaParam = MutableLiveData<MediaReq>()
    val medias: LiveData<ArrayList<Media>>

    private val photoParam = MutableLiveData<MediaReq>()
    val photos: LiveData<ArrayList<Media>>

    init {
        contents = Transformations.switchMap(contentParam) {
            repository.getContent(contentParam.value!!) }

        people = Transformations.switchMap(peopleParam) {
            repository.getPeople(peopleParam.value!!) }

        medias = Transformations.switchMap(mediaParam) {
            repository.getMedias(mediaParam.value!!.cateId!!,mediaParam.value!!.filter!!) }

        photos = Transformations.switchMap(photoParam) {
            repository.getPhotos(photoParam.value!!.cateId!!,photoParam.value!!.filter!!) }
    }

    fun setContentParam(contentParam:String) {
        this.contentParam.value = contentParam
    }

    fun setPeopleParam(peopleParam:String) {
        this.peopleParam.value = peopleParam
    }

    fun setMediaParam(mediaParam:MediaReq) {
        this.mediaParam.value = mediaParam
    }

    fun setPhotosParam(photoParam:MediaReq) {
        this.photoParam.value = photoParam
    }

    override fun onCleared() {
        super.onCleared()
        repository.onClear()
    }
}