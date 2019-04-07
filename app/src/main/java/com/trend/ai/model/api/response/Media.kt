package com.trend.ai.model.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Entities {

    @SerializedName("hashtags")
    @Expose
    var hashtags: List<Any>? = null
    @SerializedName("media")
    @Expose
    var media: List<Medium>? = null
    @SerializedName("urls")
    @Expose
    var urls: List<Any>? = null
    @SerializedName("user_mentions")
    @Expose
    var userMentions: List<Any>? = null

}


class Large {

    @SerializedName("w")
    @Expose
    var w: Double? = null
    @SerializedName("h")
    @Expose
    var h: Double? = null
    @SerializedName("resize")
    @Expose
    var resize: String? = null

}


class Media {

    @SerializedName("user")
    @Expose
    var user: User? = null
    @SerializedName("twitter_id")
    @Expose
    var twitterId: Long? = null
    @SerializedName("twitter_user_id")
    @Expose
    var twitterUserId: Long? = null
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
    var favoriteCount: Long? = null
    @SerializedName("quote_count")
    @Expose
    var quoteCount: Long? = null
    @SerializedName("reply_count")
    @Expose
    var replyCount: Long? = null
    @SerializedName("retweet_count")
    @Expose
    var retweetCount: Long? = null
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


class Medium {

    @SerializedName("indices")
    @Expose
    var indices: List<Long>? = null
    @SerializedName("display_url")
    @Expose
    var displayUrl: String? = null
    @SerializedName("expanded_url")
    @Expose
    var expandedUrl: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("twitter_id")
    @Expose
    var twitterId: Long? = null
    @SerializedName("media_url")
    @Expose
    var mediaUrl: String? = null
    @SerializedName("media_url_https")
    @Expose
    var mediaUrlHttps: String? = null
    @SerializedName("source_status_id")
    @Expose
    var sourceStatusId: Long? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("sizes")
    @Expose
    var sizes: Sizes? = null
    @SerializedName("video_info")
    @Expose
    var videoInfo: VideoInfo? = null

}


class Medium_ {

    @SerializedName("w")
    @Expose
    var w: Double? = null
    @SerializedName("h")
    @Expose
    var h: Double? = null
    @SerializedName("resize")
    @Expose
    var resize: String? = null

}


class Sizes {

    @SerializedName("thumb")
    @Expose
    var thumb: Thumb? = null
    @SerializedName("large")
    @Expose
    var large: Large? = null
    @SerializedName("medium")
    @Expose
    var medium: Medium_? = null
    @SerializedName("small")
    @Expose
    var small: Small? = null

}


class Small {

    @SerializedName("w")
    @Expose
    var w: Double? = null
    @SerializedName("h")
    @Expose
    var h: Double? = null
    @SerializedName("resize")
    @Expose
    var resize: String? = null

}


class Thumb {

    @SerializedName("w")
    @Expose
    var w: Double? = null
    @SerializedName("h")
    @Expose
    var h: Double? = null
    @SerializedName("resize")
    @Expose
    var resize: String? = null

}


class User {

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


class Variant {

    @SerializedName("content_type")
    @Expose
    var contentType: String? = null
    @SerializedName("bitrate")
    @Expose
    var bitrate: Double? = null
    @SerializedName("url")
    @Expose
    var url: String? = null

}

class VideoInfo {

    @SerializedName("aspect_ratio")
    @Expose
    var aspectRatio: List<Double>? = null
    @SerializedName("duration_millis")
    @Expose
    var durationMillis: Double? = null
    @SerializedName("variants")
    @Expose
    var variants: List<Variant>? = null

}