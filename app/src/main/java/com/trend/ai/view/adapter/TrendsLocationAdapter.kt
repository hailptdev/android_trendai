package com.trend.ai.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.trend.ai.R
import com.trend.ai.model.api.response.Topic

class TrendsLocationAdapter(private val topicDetails: List<Topic>) : RecyclerView.Adapter<TrendsLocationAdapter.MyViewHolder>() {
    var onItemClick: ((Topic) -> Unit)? = null
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.tvName)
        var tvVolume: TextView = view.findViewById(R.id.tvVolume)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(topicDetails[adapterPosition])
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.itemt_trends_location_layout, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val topic = topicDetails[position]
        holder.name.text = topic.name
        holder.tvVolume.text = topic.volume



    }

    override fun getItemCount(): Int {
        return topicDetails.size
    }
}