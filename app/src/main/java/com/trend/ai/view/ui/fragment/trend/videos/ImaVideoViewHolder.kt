/*
 * Copyright (c) 2018 Nam Nguyen, nam@ene.im
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package toro.demo.ads.ima

import android.content.Context
import android.net.Uri
import android.support.v7.widget.AppCompatImageView
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.ext.ima.ImaAdsLoader
import com.google.android.exoplayer2.ui.PlayerView
import com.trend.ai.R
import com.trend.ai.model.api.response.Media
import com.trend.ai.view.ui.fragment.trend.videos.BaseViewHolder
import im.ene.toro.ToroPlayer
import im.ene.toro.ToroUtil
import im.ene.toro.exoplayer.AdsExoPlayerViewHelper
import im.ene.toro.media.PlaybackInfo
import im.ene.toro.widget.Container


/**
 * @author eneim (2018/08/22).
 */
@Suppress("MemberVisibilityCanBePrivate")
class ImaVideoViewHolder(itemView: View, builder: ImaAdsLoader.Builder) : BaseViewHolder(itemView), ToroPlayer {

    var helper: AdsExoPlayerViewHelper? = null
    val exoPlayerView: PlayerView = itemView.findViewById(R.id.imageView3)

    var name: TextView = itemView.findViewById(R.id.tvName)
    var tvContent: TextView = itemView.findViewById(R.id.tvContent)
    var imvAva: ImageView = itemView.findViewById(R.id.imvAva)
    var tvNameScreen: TextView = itemView.findViewById(R.id.tvNameScreen)

    var tvComment: TextView = itemView.findViewById(R.id.tvComment)
    var tvReload: TextView = itemView.findViewById(R.id.tvReload)
    var tvHeart: TextView = itemView.findViewById(R.id.tvHeart)

    var posterView: AppCompatImageView = itemView.findViewById(R.id.posterView)



    var mediaUri = Uri.parse(itemView.context.getString(R.string.ima_content_url))!!
    var adTagUri = Uri.parse(itemView.context.getString(R.string.ima_ad_tag_url))!!
    val adLoader = builder.buildForAdTag(adTagUri)!!

    override fun getPlayerView() = this.exoPlayerView

    override fun getCurrentPlaybackInfo(): PlaybackInfo {
        return helper?.latestPlaybackInfo ?: PlaybackInfo()
    }

    override fun initialize(container: Container, playbackInfo: PlaybackInfo) {
        (helper ?: AdsExoPlayerViewHelper(
            this, mediaUri, null,
            adLoader, null
        ).also { helper = it }).initialize(container, playbackInfo)
    }

    override fun play() {
        Log.e("ImaVideoViewHolder "," play")
        helper?.play()
    }

    override fun pause() {
        Log.e("ImaVideoViewHolder "," pause")
//        posterView.visibility = View.VISIBLE
        helper?.pause()
    }

    override fun isPlaying(): Boolean {
//        posterView.visibility = View.GONE
        Log.e("ImaVideoViewHolder "," isPlaying")
        return helper?.isPlaying ?: false
    }

    override fun release() {
        helper?.run {
            this.release()
        }
    }

    override fun onBind(payload: Any?) {
        super.onBind(payload)
        Log.e("hailpt", " ImaVideoViewHolder $payload")

    }

    override fun onBindData(data: Media, context: Context) {
        super.onBindData(data, context)
        if (data.entities != null && data.entities!!.media!!.isNotEmpty() && data.entities!!.media!![0].videoInfo != null && data.entities!!.media!![0].videoInfo!!.variants!!.isNotEmpty()) {
            val url = data.entities!!.media!![0].videoInfo!!.variants!![0].url!!
            mediaUri = Uri.parse(url)

            Log.e("hailpt"," ~~~> mediaUri "+mediaUri)
        }

        adTagUri = Uri.parse("http://pbs.twimg.com/ext_tw_video_thumb/1114691100826931201/pu/img/fTkE-iLb4InvrH-l.jpg")

        name.text = data.user!!.name
        tvNameScreen.text = "@" + data.user!!.screenName
        tvContent.text = data.text
        Glide.with(context).load(data.user!!.profileImageUrl).into(imvAva)


//        Glide.with(context).load(data.entities!!.media!![0].mediaUrl!!).into(posterView)



        tvComment.text = data.replyCount.toString()
        tvReload.text = data.retweetCount.toString()
        tvHeart.text = data.favoriteCount.toString()
    }

    override fun wantsToPlay() = ToroUtil.visibleAreaOffset(this, itemView.parent) >= 0.75

    override fun getPlayerOrder() = adapterPosition
}