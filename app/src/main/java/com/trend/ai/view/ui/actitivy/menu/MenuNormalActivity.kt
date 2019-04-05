package com.trend.ai.view.ui.actitivy.menu

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.Toast
import com.mancj.materialsearchbar.MaterialSearchBar
import com.trend.ai.R
import com.trend.ai.core.base.BaseActivity
import com.trend.ai.databinding.ActivityMenuNormalBinding
import com.trend.ai.delegate.SearchDelegate
import com.trend.ai.model.api.response.category.CategoryRes
import com.trend.ai.view.adapter.TrendSuggestionsAdapter
import com.trend.ai.view.ui.fragment.main.MainFragment
import kotlinx.android.synthetic.main.activity_menu_normal.*
import kotlinx.android.synthetic.main.app_bar_menu_normal.*
import java.util.*

/*
* 1. Get categories
* 2. Show
* */

class MenuNormalActivity : BaseActivity<MenuViewModel, ActivityMenuNormalBinding>(),
    NavigationView.OnNavigationItemSelectedListener, MaterialSearchBar.OnSearchActionListener,


    SearchDelegate {
    var viewModel: MenuViewModel? = null

    override fun getViewModel(): Class<MenuViewModel> {
        return MenuViewModel::class.java
    }

    override fun onCreate(instance: Bundle?, viewModel: MenuViewModel?, binding: ActivityMenuNormalBinding?) {

        if (viewModel != null) {
            this.viewModel = viewModel
            init(viewModel)

        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_menu_normal
    }

    private var suggestions = ArrayList<CategoryRes>()
    private var customSuggestionsAdapter: TrendSuggestionsAdapter? = null
    private var mainFragment = MainFragment()

    private fun init(viewModel: MenuViewModel) {
        viewModel.categories.observe(this, Observer {
            Toast.makeText(this, it!![0].name, Toast.LENGTH_LONG).show()
            this.suggestions = it
            doSetupView()
            dismisProgressDialog()
        })
        showProgessDialog()
        viewModel.setLoginParam(true)
    }

    private fun getContent() {
        viewModel!!.setContentParam("5c9b2f31ceddb700010694e1")
        viewModel!!.contents.observe(this, Observer {
            if (it != null) {
                Log.e("hailpt", " ~~~ getContent " + it[0].contentScore)
            }
        })
    }


    fun doSetupView() {
        nav_view.setNavigationItemSelectedListener(this)
//        nav_view_right.setNavigationItemSelectedListener(this)
        searchBar.setOnSearchActionListener(this)
        searchBar.setSearchIcon(R.drawable.ic_search)
        searchBar.setMenuIcon(R.drawable.ic_setting)
        searchBar.setArrowIcon(R.drawable.ic_left_menu)

        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        customSuggestionsAdapter = TrendSuggestionsAdapter(inflater)
        customSuggestionsAdapter!!.searchDelegate = this
        searchBar.inflateMenu(R.menu.main)
//        searchBar.setMaxSuggestionCount(2)
        searchBar.setHint("Find Trends ...")


        customSuggestionsAdapter!!.suggestions = suggestions
        searchBar.setCustomSuggestionAdapter(customSuggestionsAdapter)


        searchBar.setCardViewElevation(10)
        searchBar.addTextChangeListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                Log.d("LOG_TAG", javaClass.simpleName + " text changed " + searchBar.text)
                customSuggestionsAdapter!!.filter.filter(searchBar.text)
            }

            override fun afterTextChanged(editable: Editable) {

            }

        })

        changeFragment(mainFragment)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                changeFragment(mainFragment)
                drawer_layout.closeDrawer(GravityCompat.START)
                getContent()
            }
            R.id.nav_gallery -> {
                changeFragment(mainFragment)
            }
            R.id.nav_slideshow -> {
                changeFragment(mainFragment)
            }
            R.id.nav_manage -> {
                changeFragment(mainFragment)
            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }

            R.id.go_premium -> {
                changeFragment(mainFragment)
                drawer_layout.closeDrawer(GravityCompat.END)
            }
        }




        return false
    }

    override fun onButtonClicked(buttonCode: Int) {

        when (buttonCode) {
            MaterialSearchBar.BUTTON_NAVIGATION -> drawer_layout.openDrawer(Gravity.LEFT)
            MaterialSearchBar.BUTTON_SPEECH -> {
            }
            MaterialSearchBar.BUTTON_BACK -> searchBar.disableSearch(true)
            MaterialSearchBar.BUTTON_SETTING -> {
                searchBar.disableSearch(false)
                drawer_layout.openDrawer(Gravity.RIGHT)
            }
        }
    }

    override fun onSearchStateChanged(enabled: Boolean) {

    }

    override fun onSearchConfirmed(text: CharSequence?) {

    }

    private fun changeFragment(targetFragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment, targetFragment, "fragment")
            .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }

    override fun doSearch(content: String?) {
        Log.d("hailpt", "MenuNormalActivity $content")
        searchBar.disableSearch(true)
        mainFragment.searchWithText(content!!)
    }

    companion object {
        fun startActivity(activity: Context) {
            val intent = Intent(activity, MenuNormalActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            activity.startActivity(intent)
        }
    }
}
