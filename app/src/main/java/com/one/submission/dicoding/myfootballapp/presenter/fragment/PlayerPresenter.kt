package com.one.submission.dicoding.myfootballapp.presenter.fragment

import com.one.submission.dicoding.myfootballapp.network.RepositoryApi
import com.one.submission.dicoding.myfootballapp.network.RepositoryCallbackApi
import com.one.submission.dicoding.myfootballapp.network.response.ResponsePlayerFootball
import com.one.submission.dicoding.myfootballapp.view.fragment.iview.PlayerView

/**
 * Dicoding Academy
 *
 * Final Project
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 10/02/19.
 */
class PlayerPresenter(val mView: PlayerView, private val repositoryApi: RepositoryApi) {

    // Load Data Last Match
    fun getPlayer(playerName:String = "") {
        mView.showLoading()
        repositoryApi.getPlayer(playerName,object : RepositoryCallbackApi<ResponsePlayerFootball?> {
            override fun onDataLoaded(data: ResponsePlayerFootball?) {
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

