package com.pramath.githubtrendingapplication.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.pramath.githubtrendingapplication.R
import com.pramath.githubtrendingapplication.common.AppConstants.EMPTY_DATA
import com.pramath.githubtrendingapplication.data.models.trending.TrendingListChildData
import kotlinx.android.synthetic.main.fragment_trending.*

class TrendingFragment : Fragment(), TrendingAdapter.TrendItemClickListener {

    private lateinit var viewModel: TrendingViewModel
    private var trendingAdapter: TrendingAdapter? = null
    private lateinit var searchItem: MenuItem

    companion object {
        fun newInstance() = TrendingFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_trending, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showProgress(true)
        viewModel = ViewModelProvider(this)[TrendingViewModel::class.java]
        initObservers()

        swipe_trend.setOnRefreshListener {
            refresh()
            Handler(Looper.getMainLooper()).postDelayed({
                swipe_trend.isRefreshing = false
            }, 1500)
        }
    }

    private fun refresh() {
        showProgress(true)
        viewModel.getTrend()
    }

    private fun initObservers() {
        viewModel.trendResLive.observe(viewLifecycleOwner) {
            showProgress(false)
            if (viewModel.trending)
                trendDataShow(it)
        }
        viewModel.progressResLive.observe(viewLifecycleOwner) {
            if (it as Int == 0) {
                showProgress(false)
            }
        }
    }

    private fun trendDataShow(trendData: List<TrendingListChildData>) {
        if (trendData.isEmpty()) {
            Toast.makeText(this.context, EMPTY_DATA, Toast.LENGTH_LONG).show()
            fragment_trending_rv.visibility = View.GONE
            empty_view.visibility = View.VISIBLE
            return
        }
        with(fragment_trending_rv) {
            fragment_trending_rv.visibility = View.VISIBLE
            empty_view.visibility = View.GONE
            trendingAdapter =
                TrendingAdapter(this@TrendingFragment)
            this.adapter = trendingAdapter
            val value = CustomLayoutManager(requireActivity(), RecyclerView.VERTICAL)
            value.setScrollEnabled(true)
            layoutManager = value
            trendingAdapter!!.setOnTrendingData(trendData)
        }
    }

    override fun onTrendItemSelected(trendingListChildData: TrendingListChildData) {
        TODO("Not yet implemented")
    }

    private fun showProgress(show: Boolean) {
        if (show) progress_loader.visibility = View.VISIBLE
        else progress_loader.visibility = View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.faq_menu, menu)
        searchItem = menu.findItem(R.id.trend_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrEmpty().not()) {
                    val items = viewModel.searchForTrend(query ?: "")
                    trendingAdapter?.setOnTrendingData(items)
                } else {
                    resetSearch()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty().not()) {
                    val items = viewModel.searchForTrend(newText ?: "")
                    trendingAdapter?.setOnTrendingData(items)
                } else {
                    resetSearch()
                }
                return true
            }

        })
    }

    private fun resetSearch() {
        trendingAdapter?.setOnTrendingData(
            (viewModel.trendResLive.value ?: emptyList())
        )
    }

}