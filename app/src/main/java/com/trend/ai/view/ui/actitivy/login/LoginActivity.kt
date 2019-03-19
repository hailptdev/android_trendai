package com.trend.ai.view.ui.actitivy.login

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.trend.ai.R
import com.trend.ai.core.base.BaseActivity
import com.trend.ai.databinding.ActivityLoginBinding
import com.trend.ai.model.api.request.LoginReq

class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>() {


    private var binding: ActivityLoginBinding? = null

    override fun getViewModel(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }

    override fun onCreate(instance: Bundle?, viewModel: LoginViewModel?, binding: ActivityLoginBinding?) {
        this.binding = binding
        if (viewModel != null) {
            init(viewModel)
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_login
    }

    private fun init(viewModel: LoginViewModel) {
//        viewModel.loginResult.observe(this, { data -> })
        viewModel.loginResult.observe(this, Observer {
            Log.e("Ahihi","OKIeeeee")
        })

        val loginReq = LoginReq()
        loginReq.email = "thunh@elcom.com.vn"
        loginReq.password = "12345678"
        viewModel.setLoginParam(loginReq)
    }
}
