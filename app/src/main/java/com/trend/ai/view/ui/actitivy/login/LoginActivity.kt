package com.trend.ai.view.ui.actitivy.login

import android.app.Activity
import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.trend.ai.R
import com.trend.ai.core.base.BaseActivity
import com.trend.ai.databinding.ActivityLoginBinding
import com.trend.ai.model.api.request.LoginReq
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

            if (it!!.status_code == 200 ){
                Log.e("Ahihi","OKIeeeee" +it.data.name)

            }
        })

        val loginReq = LoginReq()
        loginReq.email = "thunh@elcom.com.vn"
        loginReq.password = "12345678"
        viewModel.setLoginParam(loginReq)


        loginButton.callback = object : Callback<TwitterSession>() {
            override fun success(result: Result<TwitterSession>) {
                requestEmailAddress(applicationContext, result.data)
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
        const val intent_login = "login"
        const val intent_avatar = "avatar_url"
        const val intent_requestCode = 1000

        fun startActivity(activity: Activity, view: View) {
            val intent = Intent(activity, LoginActivity::class.java)
//            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                activity, view,
//                ViewCompat.getTransitionName(view)!!
//            )
//            intent.putExtra(intent_login, githubUser.login)
//            intent.putExtra(intent_avatar, githubUser.avatar_url)
//            activity.startActivityForResult(intent, 1000, options.toBundle())
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
