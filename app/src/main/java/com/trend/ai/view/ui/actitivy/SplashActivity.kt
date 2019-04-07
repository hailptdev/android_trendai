package com.trend.ai.view.ui.actitivy

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.trend.ai.R
import com.trend.ai.util.Config
import com.trend.ai.util.PreferUtils
import com.trend.ai.view.ui.actitivy.login.LoginActivity
import com.trend.ai.view.ui.actitivy.menu.MenuLeftActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
//        PreferUtils.setUserToken(baseContext,"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NTQ1MzkxOTUsInVpZCI6IjVjOWYwMGU4Y2VkZGI3MDAwMTA2YjMzMSJ9.2FY3Ugi8Gv6nzazfz3Z4QBamYhkdK4QCnlgy7DoZGK4")
        if(PreferUtils.getUserToken(baseContext) != ""){
            MenuLeftActivity.startActivity(application)
            Config.TOKEN = PreferUtils.getUserToken(baseContext)

//            ChooseLocationActivity.startActivity(application)
        } else {
            LoginActivity.startActivity(application)
//            ChooseLocationActivity.startActivity(application)
        }

        finish()
    }
}
