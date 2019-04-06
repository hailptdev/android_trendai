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
import com.trend.ai.model.api.response.Influencer

class PeopleAdapter(private val topicDetails: List<Influencer>, private val context:Context) : RecyclerView.Adapter<PeopleAdapter.MyViewHolder>() {
    var onItemClick: ((Influencer) -> Unit)? = null
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.tvName)
        var imvAva: ImageView = view.findViewById(R.id.imvAva)
        var tvNameScreen: TextView = view.findViewById(R.id.tvNameScreen)


        var tvFriends: TextView = view.findViewById(R.id.tvFriends)
        var tvFollowers: TextView = view.findViewById(R.id.tvFollowers)
        var tvHeart: TextView = view.findViewById(R.id.tvHeart)
        var tvScore: TextView = view.findViewById(R.id.tvScore)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(topicDetails[adapterPosition])
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_trend_people_layout, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = topicDetails[position]
        holder.name.text = data.name
        holder.tvNameScreen.text = "@"+data.screen_name
        Glide.with(context).load(data.profile_image_url).into(holder.imvAva)

        holder.tvFriends.text = data.friends_count.toString()
        holder.tvFollowers.text = data.followers_count.toString()
        holder.tvHeart.text = data.like_count.toString()
        holder.tvScore.text = data.influencer_score.toString()




    }

    override fun getItemCount(): Int {
        return topicDetails.size
    }
}