package com.pramath.githubtrendingapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pramath.githubtrendingapplication.R
import com.pramath.githubtrendingapplication.data.models.trending.TrendingListChildData
import com.pramath.githubtrendingapplication.databinding.TrendItemLayoutBinding

class TrendingAdapter(
    private val trendItemClickListener: TrendItemClickListener
) : RecyclerView.Adapter<TrendingAdapter.ViewHolder>() {
    private var trendDataList: List<TrendingListChildData> = ArrayList()

    inner class ViewHolder(private val binding: TrendItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.trendItemLayoutNameTv.text =
                trendDataList[position].name
            binding.trendItemLayoutProfileImageIv.setImageDrawable(trendDataList[position].image)

            when {
                !trendDataList[position].selected -> binding.trendItemLayoutCv.background =
                    ResourcesCompat.getDrawable(binding.root.resources, R.color.white_two, null)
                trendDataList[position].selected -> binding.trendItemLayoutCv.background =
                    ResourcesCompat.getDrawable(binding.root.resources, R.color.warm_grey, null)
            }

            binding.trendItemLayoutCv.setOnClickListener {
                when {
                    trendDataList[position].selected -> trendDataList[position].selected = false
                    !trendDataList[position].selected -> trendDataList[position].selected = true
                }
                notifyItemChanged(position)
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<TrendItemLayoutBinding>(
                inflater,
                R.layout.trend_item_layout,
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return trendDataList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(position)
    }

    fun setOnTrendingData(trendDataList: List<TrendingListChildData>) {
        this.trendDataList = trendDataList
        notifyDataSetChanged()
    }

    interface TrendItemClickListener {
        fun onTrendItemSelected(trendingListChildData: TrendingListChildData)
    }

}
