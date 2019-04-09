package com.trend.ai.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.trend.ai.view.ui.fragment.trend.VideosFragment

class TabPagerAdapter(fm: FragmentManager, private var tabCount: Int) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {

        when (position) {
            0 -> return VideosFragment()
            1 -> return VideosFragment()
            2 -> return VideosFragment()
            3 -> return VideosFragment()
            else -> return null
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}