package com.trend.ai.view.ui.actitivy.menu

import android.content.Context
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MenuItem
import com.mancj.materialsearchbar.MaterialSearchBar
import com.trend.ai.R
import com.trend.ai.delegate.SearchDelegate
import com.trend.ai.model.data.Product
import com.trend.ai.view.adapter.TrendSuggestionsAdapter
import com.trend.ai.view.ui.fragment.ProfileFragment
import com.trend.ai.view.ui.fragment.main.MainFragment
import kotlinx.android.synthetic.main.activity_menu_normal.*
import kotlinx.android.synthetic.main.app_bar_menu_normal.*
import java.util.ArrayList

class MenuNormalActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MaterialSearchBar.OnSearchActionListener,
    SearchDelegate {






    private val suggestions = ArrayList<Product>()
    private var customSuggestionsAdapter: TrendSuggestionsAdapter? = null
    private var mainFragment = MainFragment()
    private val products = arrayOf(
        "Simvastatin",
        "Carrot Daucus carota",
        "Carrot Daucus carota",
        "Carrot Daucus carota",
        "Carrot Daucus carota",
        "Carrot Daucus carota",
        "Carrot Daucus carota",
        "Carrot Daucus carota",
        "Carrot Daucus carota",
        "Sodium Fluoride",
        "White Kidney Beans",
        "Salicylic Acid",
        "cetirizine hydrochloride",
        "Mucor racemosus",
        "Thymol",
        "TOLNAFTATE",
        "Albumin Human"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_normal)
        nav_view.setNavigationItemSelectedListener(this)
//        nav_view_right.setNavigationItemSelectedListener(this)
        searchBar.setOnSearchActionListener(this)
        searchBar.setSearchIcon(R.drawable.ic_search)
        searchBar.setMenuIcon(R.drawable.ic_setting)
        searchBar.setArrowIcon(R.drawable.ic_left_menu)
        supportActionBar!!.hide();

        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        customSuggestionsAdapter = TrendSuggestionsAdapter(inflater)
        customSuggestionsAdapter!!.searchDelegate = this
        searchBar.inflateMenu(R.menu.main)
//        searchBar.setMaxSuggestionCount(2)
        searchBar.setHint("Find Trends ...")

        for (i in 1..10) {
            suggestions.add(Product(products[i - 1], i * 10))
        }

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




        return true
    }

    override fun onButtonClicked(buttonCode: Int) {

        when (buttonCode) {
            MaterialSearchBar.BUTTON_NAVIGATION -> drawer_layout.openDrawer(Gravity.LEFT)
            MaterialSearchBar.BUTTON_SPEECH -> {
            }
            MaterialSearchBar.BUTTON_BACK -> searchBar.disableSearch(true)
            MaterialSearchBar.BUTTON_SETTING ->  {
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

        val name = ProfileFragment::class.java.name
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
}
