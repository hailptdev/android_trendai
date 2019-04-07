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
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.android.exoplayer2.ext.ima.ImaAdsLoader
import com.trend.ai.R
import com.trend.ai.model.api.response.Media
import com.trend.ai.view.ui.fragment.trend.videos.BaseViewHolder

/**
 * @author eneim (2018/08/22).
 */
class ImaDemoAdapter(private val builder: ImaAdsLoader.Builder, private val medias : ArrayList<Media>, private val context:Context) : Adapter<BaseViewHolder>() {
  companion object {

    var mData = ArrayList<String>()
    init {
      mData.add("https://video.twimg.com/ext_tw_video/1114512887056199682/pu/vid/1280x720/M3fD_FfG68yHNV5E.mp4?tag=8")
      mData.add("https://video.twimg.com/ext_tw_video/1114796312887791616/pu/vid/320x180/a_5S9a3KTOxDDcMO.mp4?tag=8")
      mData.add("https://video.twimg.com/ext_tw_video/1114691100826931201/pu/vid/270x180/2X_WLVIuHOJqSQS2.mp4?tag=8")
      mData.add("https://video.twimg.com/ext_tw_video/1114512887056199682/pu/vid/1280x720/M3fD_FfG68yHNV5E.mp4?tag=8")
      mData.add("https://video.twimg.com/ext_tw_video/1114512887056199682/pu/vid/1280x720/M3fD_FfG68yHNV5E.mp4?tag=8")
      mData.add("https://video.twimg.com/ext_tw_video/1114512887056199682/pu/vid/1280x720/M3fD_FfG68yHNV5E.mp4?tag=8")
      mData.add("https://video.twimg.com/ext_tw_video/1114512887056199682/pu/vid/1280x720/M3fD_FfG68yHNV5E.mp4?tag=8")
    }

     var ITEM_COUNT = mData.size
  }



  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
    val itemView = LayoutInflater.from(parent.context).inflate(
      R.layout.vh_video_player, parent,
        false)
    return ImaVideoViewHolder(itemView, builder)
  }

  override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
    holder.onBind(position)
    holder.onBindData(medias[position],context)
  }

  override fun getItemId(position: Int): Long {
    return position.toLong()
  }

  override fun getItemCount(): Int {
    return medias.size
  }

}