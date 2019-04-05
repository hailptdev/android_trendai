package com.trend.ai.view.ui.actitivy.login

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.trend.ai.R
import com.trend.ai.core.base.BaseActivity
import com.trend.ai.databinding.ActivityLoginBinding
import com.trend.ai.model.api.request.LoginReq
import com.trend.ai.util.Config
import com.trend.ai.util.PreferUtils
import com.trend.ai.view.ui.actitivy.menu.MenuLeftActivity
import com.trend.ai.view.ui.actitivy.menu.MenuNormalActivity
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.TwitterSession
import com.twitter.sdk.android.core.identity.TwitterAuthClient
import kotlinx.android.synthetic.main.activity_login.*

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
        viewModel.loginResult.observe(this, Observer {
            if (it != null) {
                Config.TOKEN = it.token.accessToken
                PreferUtils.setUserToken(baseContext, it.token.accessToken)
                MenuLeftActivity.startActivity(application)
            }
        })

        loginButton.callback = object : Callback<TwitterSession>() {
            override fun success(result: Result<TwitterSession>) {
                PreferUtils.setToken(baseContext, result.data.authToken.token)
                PreferUtils.setSecretToken(baseContext, result.data.authToken.secret)

                val loginReq = LoginReq()
                loginReq.access_token = PreferUtils.getSecretToken(baseContext)
                loginReq.access_token_secret = PreferUtils.getToken(baseContext)

                viewModel.setLoginParam(loginReq)

//                requestEmailAddress(applicationContext, result.data)
            }

            override fun failure(exception: TwitterException) {
                // Upon error, show a toast message indicating that authorization request failed.
                Toast.makeText(
                    applicationContext, exception.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    companion object {
        fun startActivity(activity: Context) {
            val intent = Intent(activity, LoginActivity::class.java)
            activity.startActivity(intent)
        }
    }

    private fun requestEmailAddress(context: Context, session: TwitterSession) {
        TwitterAuthClient().requestEmail(session, object : Callback<String>() {
            override fun success(result: Result<String>) {
                Toast.makeText(context, result.data, Toast.LENGTH_SHORT).show()
            }

            override fun failure(exception: TwitterException) {
                Toast.makeText(context, exception.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Pass the activity result to the saveSession button.
        loginButton.onActivityResult(requestCode, resultCode, data)
    }
}
