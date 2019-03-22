package com.trend.ai.view.ui.actitivy.topic

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_favor_topic.*
import com.trend.ai.MainActivity
import com.trend.ai.view.adapter.CustomAdapter
import java.util.Arrays.asList
import java.util.*


class FavorTopicActivity : AppCompatActivity() {
    private var personNames = ArrayList(
        Arrays.asList(
            "Person 1fsfs",
            "Person 2fds",
            "Person fsdfdsfd3",
            "Person fdsfdsfdsfdsf4",
            "Person f5",
            "Person 6sd",
            "Person fdsfdsfdsf7",
            "Person 8",
            "Person fdsfdsf9",
            "Person 10",
            "Person 11fdsfdsfdsfdsfdsfs",
            "Person 12",
            "Person 1fdsfdsfdsf3",
            "Person 1fdsfdsfsd4"
        )
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.trend.ai.R.layout.activity_favor_topic)



// set a StaggeredGridLayoutManager with 3 number of columns and vertical orientation
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(3, LinearLayoutManager.HORIZONTAL)
        recyclerView.layoutManager = staggeredGridLayoutManager // set LayoutManager to RecyclerView
        val customAdapter = CustomAdapter(this, personNames)
        recyclerView.adapter = customAdapter // set the Adapter to RecyclerView

    }
}
