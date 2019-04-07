package com.trend.ai.model.api.response.category

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Country {

    @SerializedName("country")
    @Expose
    var country: String? = null
    @SerializedName("countryCode")
    @Expose
    var countryCode: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("parentid")
    @Expose
    var parentid: Int? = null
    @SerializedName("placeType")
    @Expose
    var placeType: PlaceType? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("woeid")
    @Expose
    var woeid: Int? = null

}


class PlaceType {

    @SerializedName("code")
    @Expose
    var code: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null

}