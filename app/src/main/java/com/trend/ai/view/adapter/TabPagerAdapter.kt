package com.trend.ai.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.trend.ai.view.ui.actitivy.menu.CateTrendFragment
import com.trend.ai.view.ui.actitivy.menu.LocationTrendFragment

class TabPagerAdapter(fm: FragmentManager, private var tabCount: Int) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {

        when (position) {
            0 -> return LocationTrendFragment()
            1 -> return CateTrendFragment()
            else -> return null
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}