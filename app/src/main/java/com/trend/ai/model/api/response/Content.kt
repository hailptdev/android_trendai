package com.trend.ai.model.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Content {

    @SerializedName("user")
    @Expose
    var user: User? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("twitter_id")
    @Expose
    var twitterId: Double? = null
    @SerializedName("twitter_user_id")
    @Expose
    var twitterUserId: Double? = null
    @SerializedName("text")
    @Expose
    var text: String? = null
    @SerializedName("lang")
    @Expose
    var lang: String? = null
    @SerializedName("entities")
    @Expose
    var entities: Entities? = null
    @SerializedName("favorite_count")
    @Expose
    var favoriteCount: Int? = null
    @SerializedName("quote_count")
    @Expose
    var quoteCount: Int? = null
    @SerializedName("reply_count")
    @Expose
    var replyCount: Int? = null
    @SerializedName("retweet_count")
    @Expose
    var retweetCount: Int? = null
    @SerializedName("content_score")
    @Expose
    var contentScore: Float? = null
    @SerializedName("media_score")
    @Expose
    var mediaScore: Float? = null
    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

}

class UserMention {

    @SerializedName("indices")
    @Expose
    var indices: List<Int>? = null
    @SerializedName("twitter_id")
    @Expose
    var twitterId: Double? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("screen_name")
    @Expose
    var screenName: String? = null

}

class User {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("twitter_id")
    @Expose
    var twitterId: Double? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("screen_name")
    @Expose
    var screenName: String? = null
    @SerializedName("profile_image_url")
    @Expose
    var profileImageUrl: String? = null

}

class Entities {

    @SerializedName("hashtags")
    @Expose
    var hashtags: List<Any>? = null
    @SerializedName("media")
    @Expose
    var media: List<Any>? = null
    @SerializedName("urls")
    @Expose
    var urls: List<Any>? = null
    @SerializedName("user_mentions")
    @Expose
    var userMentions: List<UserMention>? = null

}