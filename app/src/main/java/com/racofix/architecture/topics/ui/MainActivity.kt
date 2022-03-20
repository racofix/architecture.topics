package com.racofix.architecture.topics.ui

import android.os.Bundle
import com.racofix.architecture.topics.R
import com.racofix.architecture.topics.platform.BaseActivity
import com.racofix.architecture.topics.ui.movie.MovieFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, MovieFragment())
            .commit()
    }
}