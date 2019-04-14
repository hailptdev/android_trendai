package com.trend.ai.view.ui.fragment.trend


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.ads.interactivemedia.v3.api.AdEvent
import com.google.android.exoplayer2.ext.ima.ImaAdsLoader
import com.trend.ai.R
import com.trend.ai.core.base.BaseFragment
import com.trend.ai.model.api.request.MediaReq
import com.trend.ai.util.Utils
import kotlinx.android.synthetic.main.fragment_media.*
import toro.demo.ads.ima.ImaDemoAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */

class VideosFragment : BaseFragment<TrendViewModel>(), AdEvent.AdEventListener {
    override fun onAdEvent(p0: AdEvent?) {

    }

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
        return inflater.inflate(R.layout.fragment_media, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeContainer.setOnRefreshListener {
            if (Utils.topicId == "") {
                val photoReq = MediaReq()
                photoReq.cateId = Utils.cateId
                photoReq.filter = "videos"
                viewModel!!.setMediaParam(photoReq)
            } else {
                val photoReq = MediaReq()
                photoReq.cateId = Utils.topicId
                photoReq.filter = "videos"
                viewModel!!.setMediaParamByLocation(photoReq)
            }
        }
        Utils.setupColorForF5(swipeContainer)
    }

    fun init() {
        if (Utils.topicId == "") {
            viewModel!!.medias.observe(this, Observer {
                mShimmerViewContainer.stopShimmerAnimation()
                mShimmerViewContainer.visibility = View.GONE
                swipeContainer.isRefreshing = false
                native_recycler_view.layoutManager = LinearLayoutManager(requireContext())
                val adapter =
                    ImaDemoAdapter(ImaAdsLoader.Builder(requireContext()).setAdEventListener(this), it!!, context!!)
                native_recycler_view.adapter = adapter

            })
            val photoReq = MediaReq()
            photoReq.cateId = Utils.cateId
            photoReq.filter = "videos"
            viewModel!!.setMediaParam(photoReq)
        } else {
            viewModel!!.mediasByLocation.observe(this, Observer {
                mShimmerViewContainer.stopShimmerAnimation()
                mShimmerViewContainer.visibility = View.GONE
                swipeContainer.isRefreshing = false
                native_recycler_view.layoutManager = LinearLayoutManager(requireContext())
                val adapter =
                    ImaDemoAdapter(ImaAdsLoader.Builder(requireContext()).setAdEventListener(this), it!!, context!!)
                native_recycler_view.adapter = adapter

            })
            val photoReq = MediaReq()
            photoReq.cateId = Utils.topicId
            photoReq.filter = "videos"
            viewModel!!.setMediaParamByLocation(photoReq)
        }


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
