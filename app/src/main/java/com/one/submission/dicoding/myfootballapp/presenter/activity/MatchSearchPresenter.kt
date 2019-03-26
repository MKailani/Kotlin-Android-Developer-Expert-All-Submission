package com.one.submission.dicoding.myfootballapp.presenter.activity

import com.one.submission.dicoding.myfootballapp.network.RepositoryApi
import com.one.submission.dicoding.myfootballapp.network.RepositoryCallbackApi
import com.one.submission.dicoding.myfootballapp.network.response.ResponseEventFootball
import com.one.submission.dicoding.myfootballapp.network.response.ResponseMatchFootball
import com.one.submission.dicoding.myfootballapp.view.activity.iview.SearchIView

/**
 * Dicoding Academy
 *
 * Final Project
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 10/02/19.
 */
class MatchSearchPresenter(val mView: SearchIView, private val repositoryApi: RepositoryApi) {

    // Load Data Next Match
    fun doSearch(textSearch: String = "") {
        mView.showLoading()
        repositoryApi.getMatchSearch(textSearch, object : RepositoryCallbackApi<ResponseEventFootball?> {
            override fun onDataLoaded(data: ResponseEventFootball?) {
                mView.dismissLoading()
                val responseReal= data?.events?.let { ResponseMatchFootball(it) }
                mView.onDataLoaded(responseReal)
            }

            override fun onDataError() {
                mView.dismissLoading()
                mView.onDataError()
            }

        })
    }

}

