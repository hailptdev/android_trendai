package com.trend.ai.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.ui.PlayerView
import com.trend.ai.R
import com.trend.ai.model.api.response.Content



class ContentAdapter(private val topicDetails: List<Content>, private val context:Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var onItemClick: ((Content) -> Unit)? = null

    companion object {
        const val TYPE_TEXT = 0
        const val TYPE_VIDEO = 1
        const val TYPE_PICTURE = 2
    }

    inner class TextViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.tvName)
        var imvAva: ImageView = view.findViewById(R.id.imvAva)
        var tvTime: TextView = view.findViewById(R.id.tvNameScreen)
        var tvContent: TextView = view.findViewById(R.id.tvContent)

        var tvComment: TextView = view.findViewById(R.id.tvComment)
        var tvReload: TextView = view.findViewById(R.id.tvReload)
        var tvHeart: TextView = view.findViewById(R.id.tvHeart)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(topicDetails[adapterPosition])
            }
        }
    }

    inner class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.tvName)
        var imvAva: ImageView = view.findViewById(R.id.imvAva)
        var tvTime: TextView = view.findViewById(R.id.tvNameScreen)
        var tvContent: TextView = view.findViewById(R.id.tvContent)

        var tvComment: TextView = view.findViewById(R.id.tvComment)
        var tvReload: TextView = view.findViewById(R.id.tvReload)
        var tvHeart: TextView = view.findViewById(R.id.tvHeart)
        var imvPhotos: ImageView = view.findViewById(R.id.imvPhotos)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(topicDetails[adapterPosition])
            }
        }
    }

    inner class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.tvName)
        var imvAva: ImageView = view.findViewById(R.id.imvAva)
        var tvTime: TextView = view.findViewById(R.id.tvNameScreen)
        var tvContent: TextView = view.findViewById(R.id.tvContent)

        var tvComment: TextView = view.findViewById(R.id.tvComment)
        var tvReload: TextView = view.findViewById(R.id.tvReload)
        var tvHeart: TextView = view.findViewById(R.id.tvHeart)
        val exoPlayerView: PlayerView = itemView.findViewById(R.id.imageView3)
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(topicDetails[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_trend_content_layout, parent, false)


        val context = parent.context
        return when (viewType) {
            TYPE_TEXT -> {
                val view = LayoutInflater.from(context).inflate(R.layout.item_trend_content_layout, parent, false)
                TextViewHolder(view)
            }
            TYPE_VIDEO -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_trend_content_video_layout, parent, false)
                VideoViewHolder(view)
            }
            TYPE_PICTURE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_trend_content_photo_layout, parent, false)
                PhotoViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = topicDetails[position]
        when (holder.itemViewType) {
            TYPE_TEXT -> {
                setupData(holder,data)
            }

            TYPE_PICTURE -> {
                setupData(holder,data)
            }

            TYPE_VIDEO -> {
                setupData(holder,data)
            }
        }
    }

    private fun setupData(holder:RecyclerView.ViewHolder, data:Content){

        when (holder.itemViewType) {
            TYPE_TEXT -> {
                val cellViewHolder = holder as TextViewHolder
                holder.tvContent.text = data.text
                holder.name.text = data.user!!.name
                holder.tvTime.text = "@"+ data.user!!.screenName
                Glide.with(context).load(data.user!!.profileImageUrl).into(holder.imvAva)

                holder.tvComment.text = data.replyCount.toString()
                holder.tvReload.text = data.retweetCount.toString()
                holder.tvHeart.text = data.favoriteCount.toString()
            }

            TYPE_PICTURE -> {
                val cellViewHolder = holder as PhotoViewHolder
                holder.tvContent.text = data.text
                holder.name.text = data.user!!.name
                holder.tvTime.text = "@"+ data.user!!.screenName
                Glide.with(context).load(data.user!!.profileImageUrl).into(holder.imvAva)

                holder.tvComment.text = data.replyCount.toString()
                holder.tvReload.text = data.retweetCount.toString()
                holder.tvHeart.text = data.favoriteCount.toString()

                Glide.with(context).load(data.entities!!.media!![0].mediaUrl).into(holder.imvPhotos)
            }

            TYPE_VIDEO -> {
                val cellViewHolder = holder as VideoViewHolder
                holder.tvContent.text = data.text
                holder.name.text = data.user!!.name
                holder.tvTime.text = "@"+ data.user!!.screenName
                Glide.with(context).load(data.user!!.profileImageUrl).into(holder.imvAva)

                holder.tvComment.text = data.replyCount.toString()
                holder.tvReload.text = data.retweetCount.toString()
                holder.tvHeart.text = data.favoriteCount.toString()
            }

        }
    }

    override fun getItemCount(): Int {
        return topicDetails.size
    }

    override fun getItemViewType(position: Int): Int {
//
        when {
            topicDetails[position].entities!!.media!!.isEmpty() -> return TYPE_TEXT
            topicDetails[position].entities!!.media!![0].type == "photo" -> return TYPE_PICTURE
            else -> return TYPE_VIDEO
        }
    }
}