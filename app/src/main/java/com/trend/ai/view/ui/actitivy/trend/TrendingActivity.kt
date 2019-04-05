package com.trend.ai.view.ui.actitivy.trend

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.trend.ai.R

class TrendingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trending)
    }

    companion object {
        fun startActivity(activity: Context) {
            val intent = Intent(activity, TrendingActivity::class.java)
            activity.startActivity(intent)
        }
    }
}
