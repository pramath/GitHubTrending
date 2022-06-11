package com.pramath.githubtrendingapplication.data.repository

import androidx.core.content.ContextCompat
import com.pramath.githubtrendingapplication.R
import com.pramath.githubtrendingapplication.TrendingApp
import com.pramath.githubtrendingapplication.common.AppConstants.FORWARD_SLASH
import com.pramath.githubtrendingapplication.common.AppConstants.H1
import com.pramath.githubtrendingapplication.common.AppConstants.SPAN
import com.pramath.githubtrendingapplication.common.AppConstants.URL_PATH
import com.pramath.githubtrendingapplication.data.models.trending.TrendingListChildData
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

object Repository {
    fun getTrending(): List<TrendingListChildData> {
        val doc: Document = Jsoup.connect(URL_PATH).get()
        val spanValue: String = doc.select(H1).select(SPAN).text().toString()
        val nameList: List<String> = spanValue.split(FORWARD_SLASH)
        val trendList: MutableList<TrendingListChildData> = ArrayList()
        for (i in nameList.indices) {
            if (i != nameList.size - 1) {
                val trendingListChildData = TrendingListChildData(
                    nameList[i], ContextCompat.getDrawable(
                        TrendingApp.context,
                        R.drawable.ic_trending
                    )
                )
                trendList.add(trendingListChildData)
            }
        }
        return trendList
    }
}
