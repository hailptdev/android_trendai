package com.trend.ai.view.ui.actitivy.menu

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.trend.ai.core.base.BaseActivity
import com.trend.ai.databinding.ActivityMenuLeftBinding
import com.trend.ai.view.adapter.TabPagerAdapter
import com.trend.ai.view.ui.actitivy.trend.TrendingActivity
import kotlinx.android.synthetic.main.activity_menu_left.*
import kotlinx.android.synthetic.main.app_bar_menu_left.*


class MenuLeftActivity : BaseActivity<MenuViewModel, ActivityMenuLeftBinding>(),
    NavigationView.OnNavigationItemSelectedListener {


    override fun getViewModel(): Class<MenuViewModel> {
        return MenuViewModel::class.java
    }

    override fun onCreate(instance: Bundle?, viewModel: MenuViewModel?, binding: ActivityMenuLeftBinding?) {
        setSupportActionBar(toolbar)
        toolbar.title = "Trending"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }

        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            com.trend.ai.R.string.navigation_drawer_open,
            com.trend.ai.R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        if (viewModel != null) {
            init(viewModel)
        }
    }

    override fun getLayoutResId(): Int {
        return com.trend.ai.R.layout.activity_menu_left
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(com.trend.ai.R.menu.menu_left, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            com.trend.ai.R.id.action_settings -> {
                TrendingActivity.start(application,"5c9b2f33ceddb700010694f5")
                return true
            }


            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            com.trend.ai.R.id.nav_camera -> {
                // Handle the camera action
            }
            com.trend.ai.R.id.nav_gallery -> {

            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onResume() {
        super.onResume()
//        mShimmerViewContainer.startShimmerAnimation()
    }

    override fun onPause() {
        super.onPause()
//        mShimmerViewContainer.stopShimmerAnimation()

    }


    private fun init(viewModel: MenuViewModel) {

        viewModel.userInfomation.observe(this, Observer {
            //            mShimmerViewContainer.stopShimmerAnimation()
//            mShimmerViewContainer.visibility = View.GONE
//            val mAdapter = AchievementAdapter(it!!.interestCategories)
//            rcView.layoutManager = LinearLayoutManager(baseContext)
//            rcView.setHasFixedSize(false)
//            rcView.adapter = mAdapter
//            mAdapter.onItemClick = { cate ->
////                viewModel.setContentParam(cate.id!!)
//
//
//                TrendingActivity.start(this, cate.id)
//
//
//            }
        })
        viewModel.setGetInfomationParam(true)



        viewModel.contents.observe(this, Observer {
            Toast.makeText(this, it!![0].text, Toast.LENGTH_LONG).show()
            Log.e("hailpt", " " + it.size)
        })

        viewModel.categories.observe(this, Observer {


        })
        viewModel.setLoginParam(true)


        configureTabLayout()

    }

    private fun configureTabLayout() {

        tabs.addTab(tabs.newTab().setText("Location"))
        tabs.addTab(tabs.newTab().setText("Category"))

        val adapter = TabPagerAdapter(supportFragmentManager,
            tabs.tabCount)
        viewpager.adapter = adapter

        viewpager.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewpager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

        })
    }



    companion object {
        fun startActivity(activity: Context) {
            val intent = Intent(activity, MenuLeftActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            activity.startActivity(intent)
        }
    }




}


