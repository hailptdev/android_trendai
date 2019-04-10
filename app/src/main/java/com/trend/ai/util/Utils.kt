package com.trend.ai.util

import android.support.v4.widget.SwipeRefreshLayout

object Utils {

    fun setupColorForF5(swipeRefreshLayout: SwipeRefreshLayout){
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
            android.R.color.holo_blue_light,
            android.R.color.holo_blue_light,
            android.R.color.holo_blue_light);
    }

    val testCateId = "5c9b2f32ceddb700010694e3"
}