package com.trend.ai.view.ui.actitivy.menu


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trend.ai.R
import com.trend.ai.core.base.BaseFragment
import com.trend.ai.util.Utils
import com.trend.ai.view.adapter.AchievementAdapter
import com.trend.ai.view.ui.actitivy.trend.CategoryTrendingActivity
import kotlinx.android.synthetic.main.fragment_category_trend.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class CateTrendFragment : BaseFragment<MenuViewModel>() {

    var viewModel:MenuViewModel? = null
    override fun getViewModel(): Class<MenuViewModel> {
        return MenuViewModel::class.java
    }

    override fun onCreate(instance: Bundle?, viewModel: MenuViewModel?) {
        this.viewModel = viewModel


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category_trend, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeContainer.setOnRefreshListener {
            viewModel!!.setGetInfomationParam(true)
        }
        Utils.setupColorForF5(swipeContainer)

        viewModel!!.userInfomation.observe(this, Observer {
            swipeContainer.isRefreshing = false
            mShimmerViewContainer.stopShimmerAnimation()
            mShimmerViewContainer.visibility = View.GONE
            val mAdapter = AchievementAdapter(it!!.interestCategories)
            rcView.layoutManager = LinearLayoutManager(context)
            rcView.setHasFixedSize(false)
            rcView.adapter = mAdapter
            mAdapter.onItemClick = { cate ->
//                viewModel.setContentParam(cate.id!!)
                Utils.topicId = ""
                Utils.cateId = cate.id
                CategoryTrendingActivity.start(activity!!, cate.id)


            }
        })
        viewModel!!.setGetInfomationParam(true)

    }


    override fun onResume() {
        super.onResume()
        mShimmerViewContainer.startShimmerAnimation()
    }

    override fun onPause() {
        super.onPause()
        mShimmerViewContainer.stopShimmerAnimation()

    }


}
