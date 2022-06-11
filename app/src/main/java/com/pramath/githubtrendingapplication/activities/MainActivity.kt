package com.pramath.githubtrendingapplication.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pramath.githubtrendingapplication.R
import com.pramath.githubtrendingapplication.ui.TrendingFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, TrendingFragment.newInstance())
                .commitNow()
        }
    }
}