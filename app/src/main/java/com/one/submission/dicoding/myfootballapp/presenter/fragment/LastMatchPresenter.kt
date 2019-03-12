package com.one.submission.dicoding.myfootballapp.presenter.fragment

import com.one.submission.dicoding.myfootballapp.network.RepositoryApi
import com.one.submission.dicoding.myfootballapp.network.RepositoryCallbackApi
import com.one.submission.dicoding.myfootballapp.network.response.ResponseMatchFootball
import com.one.submission.dicoding.myfootballapp.view.fragment.iview.CommonView

/**
 * Dicoding Academy
 *
 * Submission 4
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 06/02/19.
 */
class LastMatchPresenter(val mView: CommonView, private val repositoryApi: RepositoryApi) {

    // Load Data Last Match
    fun doLastMatch(id: String) {
        mView.showLoading()
        repositoryApi.getLastMatch(id, object : RepositoryCallbackApi<ResponseMatchFootball?> {
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

}

