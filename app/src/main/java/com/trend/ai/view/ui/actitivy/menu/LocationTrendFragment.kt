package com.trend.ai.view.ui.actitivy.menu


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.trend.ai.R
import com.trend.ai.core.base.BaseFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class LocationTrendFragment : BaseFragment<MenuViewModel>() {

    var viewModel: MenuViewModel? = null
    override fun getViewModel(): Class<MenuViewModel> {
        return MenuViewModel::class.java
    }

    override fun onCreate(instance: Bundle?, viewModel: MenuViewModel?) {
        this.viewModel = viewModel
        if (viewModel != null) {
            init()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category_trend, container, false)
    }


    fun init() {
        viewModel!!.trendsByLocation.observe(this, Observer {
            Log.e("hailpt", " OK")
        })

        viewModel!!.setTrendsByLocationParam(true)
    }


}
