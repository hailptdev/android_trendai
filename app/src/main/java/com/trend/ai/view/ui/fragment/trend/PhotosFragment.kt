package com.trend.ai.view.ui.fragment.trend


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trend.ai.R
import com.trend.ai.core.base.BaseFragment
import com.trend.ai.model.api.request.MediaReq
import com.trend.ai.util.Utils
import com.trend.ai.view.adapter.PhotosAdapter
import com.trend.ai.view.ui.actitivy.trend.ImageDetailActivity
import kotlinx.android.synthetic.main.fragment_content.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */

class PhotosFragment : BaseFragment<TrendViewModel>() {
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
            val photoReq = MediaReq()
            photoReq.cateId = "5c9b2f33ceddb700010694f5"
            photoReq.filter = "images"
            viewModel!!.setPhotosParam(photoReq)
        }
        Utils.setupColorForF5(swipeContainer)
    }

    fun init() {

        viewModel!!.photos.observe(this, Observer {
            mShimmerViewContainer.stopShimmerAnimation()
            mShimmerViewContainer.visibility = View.GONE
            val mAdapter = PhotosAdapter(it!!,context!!)
            rcView.layoutManager = LinearLayoutManager(activity)
            rcView.setHasFixedSize(false)
            rcView.adapter = mAdapter
            mAdapter.onItemClick = { cate,view ->
                val viewAnimation = view.findViewById<View>(R.id.imvPhoto)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity!!,
                    viewAnimation,
                    getString(R.string.trans_shared_image)
                )
                 ImageDetailActivity.start(context!!,cate.entities!!.media!![0].mediaUrl!!,options)
            }
            swipeContainer.isRefreshing = false

        })

        val photoReq = MediaReq()
        photoReq.cateId = "5c9b2f33ceddb700010694f5"
        photoReq.filter = "images"
        viewModel!!.setPhotosParam(photoReq)


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
