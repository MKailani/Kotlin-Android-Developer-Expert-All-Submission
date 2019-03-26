package com.one.submission.dicoding.myfootballapp.view.fragment.iview

import com.one.submission.dicoding.myfootballapp.model.Team
import com.one.submission.dicoding.myfootballapp.network.RepositoryCallbackApi
import com.one.submission.dicoding.myfootballapp.network.response.ResponseLeagueFootball
import com.one.submission.dicoding.myfootballapp.network.response.ResponseTeamFootball
import com.one.submission.dicoding.myfootballapp.view.BaseView

/**
 * Dicoding Academy
 *
 * Final Project
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 10/02/19.
 */
interface TeamView : BaseView, RepositoryCallbackApi<ResponseTeamFootball?> {
    fun setupRecycler()
    fun setupListener()
    fun loadData()
    fun loadSpinner(data: ResponseLeagueFootball?)
    fun goToNextActivity(team: Team)

}