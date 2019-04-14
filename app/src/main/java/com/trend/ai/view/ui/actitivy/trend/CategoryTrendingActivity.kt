package com.trend.ai.view.ui.actitivy.trend

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.trend.ai.R
import com.trend.ai.view.ui.fragment.trend.ContentFragment
import com.trend.ai.view.ui.fragment.trend.InfluencerFragment
import com.trend.ai.view.ui.fragment.trend.PhotosFragment
import com.trend.ai.view.ui.fragment.trend.VideosFragment
import kotlinx.android.synthetic.main.activity_trending.*


class CategoryTrendingActivity : AppCompatActivity() {

    var CATEGORY_ID_KEY = "CATEGORY_ID_KEY"
    var cateId = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trending)
        setSupportActionBar(toolbar)
        toolbar.title = "Trending"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        setupViewPager(viewpager)
        tabs.setupWithViewPager(viewpager);

        toolbar.setNavigationOnClickListener { onBackPressed() }

        if (intent != null){
            cateId = intent.getStringExtra("CATEGORY_ID_KEY")
            Log.e("hailpt"," TrendingActivity ~~> "+cateId)
        }

    }


    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(ContentFragment(), "Top")
//        adapter.addFragment(TopicFragment(), "Lasted")
        adapter.addFragment(InfluencerFragment(), "Influencers")
        adapter.addFragment(PhotosFragment(), "Photos")
        adapter.addFragment(VideosFragment(), "Videos")
        viewPager.adapter = adapter
    }

    internal inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList.get(position)
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList.get(position)
        }
    }

    companion object {
        fun start(activity: Context,id:String) {
            val intent = Intent(activity, CategoryTrendingActivity::class.java)
            intent.putExtra("CATEGORY_ID_KEY", id)
            activity.startActivity(intent)
        }
    }


}
