package com.trend.ai.view.ui.actitivy.trend

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_image_detail.*


class ImageDetailActivity : AppCompatActivity(), View.OnTouchListener  {

    private var _xDelta: Int = 0
    private var _yDelta: Int = 0
    var imageUrl = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(com.trend.ai.R.layout.activity_image_detail)

        if (intent != null && intent.hasExtra("IMAGE_URL_ID_KEY")){
            imageUrl = intent.getStringExtra("IMAGE_URL_ID_KEY")
            if(imageUrl != ""){
                Glide.with(this).load(imageUrl).into(imvDetail)
            }
            imvDetail.setOnTouchListener(this)
        }

    }

    override fun onTouch(view: View, event: MotionEvent): Boolean {
        val X = event.rawX.toInt()
        val Y = event.rawY.toInt()
        when (event.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN -> {
                val lParams = view.layoutParams as RelativeLayout.LayoutParams
                _xDelta = X - lParams.leftMargin
                _yDelta = Y - lParams.topMargin

                Log.e("hailpt"," onTouch " +_yDelta )


            }
            MotionEvent.ACTION_UP -> {


            }
            MotionEvent.ACTION_POINTER_DOWN -> {
            }
            MotionEvent.ACTION_POINTER_UP -> {
            }
            MotionEvent.ACTION_MOVE -> {
                val layoutParams = view
                    .layoutParams as RelativeLayout.LayoutParams
                layoutParams.leftMargin = _xDelta
                layoutParams.topMargin = Y - _yDelta
                layoutParams.rightMargin = -150
                layoutParams.bottomMargin = -150
                view.layoutParams = layoutParams

                if (_yDelta > 420){
                    ActivityCompat.finishAfterTransition(this)
                }
            }
        }
        root.invalidate()
        return true
    }

    override fun onBackPressed() {
        ActivityCompat.finishAfterTransition(this)
    }

    companion object {
        fun start(activity: Context, id:String) {
            val intent = Intent(activity, ImageDetailActivity::class.java)
            intent.putExtra("IMAGE_URL_ID_KEY", id)
            activity.startActivity(intent)
        }

        fun start(activity: Context, id:String, options: ActivityOptionsCompat) {
            val intent = Intent(activity, ImageDetailActivity::class.java)
            intent.putExtra("IMAGE_URL_ID_KEY", id)


            activity.startActivity(intent, options.toBundle())


        }
    }
}
