package com.trend.ai.view.ui.fragment.trend


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
import com.trend.ai.view.adapter.ContentAdapter
import kotlinx.android.synthetic.main.fragment_content.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ContentFragment : BaseFragment<TrendViewModel>() {

    var cateId = ""
    var viewModel: TrendViewModel? = null
    override fun getViewModel(): Class<TrendViewModel> {
        return TrendViewModel::class.java
    }

    override fun onCreate(instance: Bundle?, viewModel: TrendViewModel?) {
        if (viewModel != null) {
            this.viewModel = viewModel
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        init()
        return inflater.inflate(R.layout.fragment_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeContainer.setOnRefreshListener {
            viewModel!!.setContentParam(Utils.testCateId)
        }
        Utils.setupColorForF5(swipeContainer)
    }

    fun init() {

        viewModel!!.contents.observe(this, Observer {
            mShimmerViewContainer.stopShimmerAnimation()
            mShimmerViewContainer.visibility = View.GONE
            val mAdapter = ContentAdapter(it!!,context!!)
            rcView.layoutManager = LinearLayoutManager(activity)
            rcView.setHasFixedSize(false)
            rcView.adapter = mAdapter
            mAdapter.onItemClick = { cate ->

            }
            swipeContainer.isRefreshing = false

        })
        viewModel!!.setContentParam(Utils.testCateId)


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
