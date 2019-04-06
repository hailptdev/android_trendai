package com.trend.ai.view.ui.actitivy.login

import android.app.Activity
import android.os.Bundle
import com.trend.ai.R
import com.trend.ai.di.module.NetworkModule

class ChooseLocationActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_location)

        NetworkModule.BASE_URL2 = ""

    }
}
