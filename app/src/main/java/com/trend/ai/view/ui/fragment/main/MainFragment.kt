package com.trend.ai.view.ui.fragment.main


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.trend.ai.R
import com.trend.ai.core.base.BaseFragment
import com.trend.ai.model.api.request.LoginReq
import kotlinx.android.synthetic.main.fragment_main.*


/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class MainFragment : BaseFragment<MainViewModel>() {
    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun onCreate(instance: Bundle?, viewModel: MainViewModel?) {

        viewModel!!.loginResult.observe(this, Observer {

        })

        val loginReq = LoginReq()
//        loginReq.email = "thunh@elcom.com.vn"
//        loginReq.password = "12345678"
        viewModel.setLoginParam(loginReq)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    public fun searchWithText(content:String){
        tvContent.text = content
    }



}
