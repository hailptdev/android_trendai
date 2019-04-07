package com.trend.ai.view.ui.actitivy.login


import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.trend.ai.R
import com.trend.ai.core.base.BaseActivity
import com.trend.ai.databinding.ActivityChooseLocationBinding
import com.trend.ai.di.module.NetworkModule
import com.trend.ai.util.Config
import com.trend.ai.util.PreferUtils

class ChooseLocationActivity : BaseActivity<LoginViewModel, ActivityChooseLocationBinding>() {


    override fun getViewModel(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }

    override fun onCreate(instance: Bundle?, viewModel: LoginViewModel?, binding: ActivityChooseLocationBinding?) {

        NetworkModule.BASE_URL2 = NetworkModule.BASE_URL_TWITTER
        Config.TOKEN =  PreferUtils.getToken(baseContext)


        if (viewModel != null){
            viewModel.countriesResult.observe(this, Observer {

                Log.e("hailpt"," ~~> "+it!!.size)

            })
            viewModel.setCountries(true)
        }




    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_choose_location
    }

    companion object {
        fun startActivity(activity: Context) {
            val intent = Intent(activity, ChooseLocationActivity::class.java)
            activity.startActivity(intent)
        }
    }




}
