package com.trend.ai.view.ui.actitivy.menu

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.trend.ai.R
import com.trend.ai.core.base.BaseActivity
import com.trend.ai.databinding.ActivityMenuLeftBinding
import com.trend.ai.model.api.response.category.CategoryRes
import com.trend.ai.view.adapter.AchievementAdapter
import com.trend.ai.view.ui.actitivy.trend.TrendingActivity
import kotlinx.android.synthetic.main.activity_menu_left.*
import kotlinx.android.synthetic.main.app_bar_menu_left.*
import kotlinx.android.synthetic.main.content_menu_left.*

class MenuLeftActivity : BaseActivity<MenuViewModel, ActivityMenuLeftBinding>(), NavigationView.OnNavigationItemSelectedListener {
    override fun getViewModel(): Class<MenuViewModel> {
        return MenuViewModel::class.java
    }

    override fun onCreate(instance: Bundle?, viewModel: MenuViewModel?, binding: ActivityMenuLeftBinding?) {
        setSupportActionBar(toolbar)
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_left_menu)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        if(viewModel != null){
            init(viewModel)
        }
    }

    override fun getLayoutResId(): Int {
       return R.layout.activity_menu_left
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_left, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onResume() {
        super.onResume()
        mShimmerViewContainer.startShimmerAnimation()
    }

    override fun onPause() {
        super.onPause()
        mShimmerViewContainer.stopShimmerAnimation()

    }


    var mList = ArrayList<CategoryRes>()
    private fun init(viewModel: MenuViewModel) {

        viewModel.contents.observe(this, Observer {
            Toast.makeText(this, it!![0].text, Toast.LENGTH_LONG).show()
            Log.e("hailpt"," "+it.size)
        })

        viewModel.categories.observe(this, Observer {


            mShimmerViewContainer.stopShimmerAnimation()
            mShimmerViewContainer.visibility = View.GONE

            mList.addAll(it!!)
            mList.addAll(it)

            val mAdapter = AchievementAdapter(mList)
            rcView.layoutManager = LinearLayoutManager(baseContext)
            rcView.setHasFixedSize(false)
            rcView.adapter = mAdapter

            mAdapter.onItemClick = { cate ->
//                TrendingActivity.startActivity(application)
                viewModel.setContentParam(cate.id!!)
            }
        })
        viewModel.setLoginParam(true)




    }

    companion object {
        fun startActivity(activity: Context) {
            val intent = Intent(activity, MenuLeftActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            activity.startActivity(intent)
        }
    }
}
