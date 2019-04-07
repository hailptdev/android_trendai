package com.trend.ai.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.trend.ai.R
import com.trend.ai.model.api.response.Media

class PhotosAdapter(private val topicDetails: List<Media>, private val context:Context) : RecyclerView.Adapter<PhotosAdapter.MyViewHolder>() {
    var onItemClick: ((Media,View) -> Unit)? = null
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.tvName)
        var imvAva: ImageView = view.findViewById(R.id.imvAva)
        var tvNameScreen: TextView = view.findViewById(R.id.tvNameScreen)
        var tvContent: TextView = view.findViewById(R.id.tvContent)
        var imvPhoto: ImageView = view.findViewById(R.id.imvPhotos)

        var tvComment: TextView = itemView.findViewById(R.id.tvComment)
        var tvReload: TextView = itemView.findViewById(R.id.tvReload)
        var tvHeart: TextView = itemView.findViewById(R.id.tvHeart)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(topicDetails[adapterPosition],itemView)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_trend_photo_layout, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = topicDetails[position]
        holder.name.text = data.user!!.name
        holder.tvContent.text = data.text
        holder.tvNameScreen.text = "@"+data.user!!.screenName

        Glide.with(context).load(data.user!!.profileImageUrl).into(holder.imvAva)
        Glide.with(context).load(data.entities!!.media!![0].mediaUrl).into(holder.imvPhoto)

        holder.tvComment.text = data.replyCount.toString()
        holder.tvReload.text = data.retweetCount.toString()
        holder.tvHeart.text = data.favoriteCount.toString()




    }

    override fun getItemCount(): Int {
        return topicDetails.size
    }
}