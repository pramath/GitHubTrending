package com.pramath.githubtrendingapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pramath.githubtrendingapplication.common.isInternetAvailable
import com.pramath.githubtrendingapplication.data.models.trending.TrendingListChildData
import com.pramath.githubtrendingapplication.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class TrendingViewModel : ViewModel() {

    var trending = false

    private var _trendRes =
        MutableLiveData<List<TrendingListChildData>>()
    val trendResLive: LiveData<List<TrendingListChildData>> get() = _trendRes

    private var _progress = MutableLiveData<Int>()
    val progressResLive: LiveData<Int> get() = _progress

    init {
        getTrend()
    }

    fun getTrend() {
        if (!isInternetAvailable()) {
            _progress.postValue(0)
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            val res = Repository.getTrending()
            trending = true
            _trendRes.postValue(res)
        }
    }

    fun searchForTrend(query: String): List<TrendingListChildData> {
        return (_trendRes.value?.filter {
            it.name?.lowercase(Locale.getDefault())
                ?.contains(query.lowercase(Locale.getDefault())) ?: false
        }?.toList() ?: emptyList())
    }

}