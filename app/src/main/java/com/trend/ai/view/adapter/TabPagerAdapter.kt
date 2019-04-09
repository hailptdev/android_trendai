package com.trend.ai.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.trend.ai.view.ui.actitivy.menu.CategoryTrendFragment
import com.trend.ai.view.ui.actitivy.menu.LocationTrendFragment
import com.trend.ai.view.ui.fragment.trend.VideosFragment

class TabPagerAdapter(fm: FragmentManager, private var tabCount: Int) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {

        when (position) {
            0 -> return LocationTrendFragment()
            1 -> return CategoryTrendFragment()
            else -> return null
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}