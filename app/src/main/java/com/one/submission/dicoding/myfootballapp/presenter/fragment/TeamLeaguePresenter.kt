package com.one.submission.dicoding.myfootballapp.presenter.fragment

import com.one.submission.dicoding.myfootballapp.network.RepositoryApi
import com.one.submission.dicoding.myfootballapp.network.RepositoryCallbackApi
import com.one.submission.dicoding.myfootballapp.network.response.ResponseLeagueFootball
import com.one.submission.dicoding.myfootballapp.network.response.ResponseTeamFootball
import com.one.submission.dicoding.myfootballapp.view.fragment.iview.TeamView

/**
 * Dicoding Academy
 *
 * Final Project
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 10/02/19.
 */
class TeamLeaguePresenter(val mView: TeamView, private val repositoryApi: RepositoryApi) {

    // Load Data Last Match
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

    // Get All team
    fun getAllTeam(team: String){
        mView.showLoading()
        repositoryApi.getAllTeam(team,object : RepositoryCallbackApi<ResponseTeamFootball?> {
            override fun onDataLoaded(data: ResponseTeamFootball?) {
                mView.dismissLoading()
                mView.onDataLoaded(data)
            }

            override fun onDataError() {
                mView.dismissLoading()
                mView.onDataError()
            }

        })
    }
    fun doSearchTeam(team: String){
        mView.showLoading()
        repositoryApi.getTeamSearch(team,object : RepositoryCallbackApi<ResponseTeamFootball?> {
            override fun onDataLoaded(data: ResponseTeamFootball?) {
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

