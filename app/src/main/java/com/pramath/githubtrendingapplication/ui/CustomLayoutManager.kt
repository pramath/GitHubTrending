package com.pramath.githubtrendingapplication.ui

import android.content.Context
import android.graphics.PointF
import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView

class CustomLayoutManager(context: Context, orientation: Int) :
    LinearLayoutManager(context, orientation, false) {
    private val mContext: Context = context
    private var isScrollEnabled = true
    override fun smoothScrollToPosition(
        recyclerView: RecyclerView,
        state: RecyclerView.State, position: Int
    ) {
        val smoothScroller: LinearSmoothScroller = object : LinearSmoothScroller(mContext) {
            //This controls the direction in which smoothScroll looks
            //for your view
            override fun computeScrollVectorForPosition(targetPosition: Int): PointF? {
                return this@CustomLayoutManager
                    .computeScrollVectorForPosition(targetPosition)
            }

            //This returns the milliseconds it takes to
            //scroll one pixel.
            override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
                return MILLISECONDS_PER_INCH / displayMetrics.densityDpi
            }
        }
        smoothScroller.targetPosition = position
        startSmoothScroll(smoothScroller)
    }

    companion object {
        private const val MILLISECONDS_PER_INCH = 50f
    }

    /* override fun canScrollHorizontally(): Boolean {
         return false
     }*/
    fun setScrollEnabled(flag: Boolean) {
        this.isScrollEnabled = flag
    }

    override fun canScrollHorizontally(): Boolean {
        //Similarly we can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollHorizontally()
    }

}