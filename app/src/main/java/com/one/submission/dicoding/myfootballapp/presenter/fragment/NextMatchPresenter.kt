package com.one.submission.dicoding.myfootballapp.presenter.fragment

import com.one.submission.dicoding.myfootballapp.network.RepositoryApi
import com.one.submission.dicoding.myfootballapp.network.RepositoryCallbackApi
import com.one.submission.dicoding.myfootballapp.network.response.ResponseLeagueFootball
import com.one.submission.dicoding.myfootballapp.network.response.ResponseMatchFootball
import com.one.submission.dicoding.myfootballapp.view.fragment.iview.CommonView

/**
 * Dicoding Academy
 *
 * Final Project
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 10/02/19.
 */
class NextMatchPresenter(val mView: CommonView, private val repositoryApi: RepositoryApi) {

    // Load Data Next Match
    fun doNextMatch(id: String) {
        mView.showLoading()
        repositoryApi.getNextMatch(id, object : RepositoryCallbackApi<ResponseMatchFootball?> {
            override fun onDataLoaded(data: ResponseMatchFootball?) {
                mView.dismissLoading()
                mView.onDataLoaded(data)
            }

            override fun onDataError() {
                mView.dismissLoading()
                mView.onDataError()
            }

        })
    }

    fun getAllLeague() {
        mView.showLoading()
        repositoryApi.getLeague(object : RepositoryCallbackApi<ResponseLeagueFootball?> {
            override fun onDataLoaded(data: ResponseLeagueFootball?) {
                mView.dismissLoading()
                mView.loadSpinner(data)
            }

            override fun onDataError() {
                mView.dismissLoading()
                mView.onDataError()
            }

        })
    }
}

