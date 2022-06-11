package com.pramath.githubtrendingapplication

import android.app.Application
import android.content.Context

class TrendingApp : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        lateinit var context: Context
    }
}

