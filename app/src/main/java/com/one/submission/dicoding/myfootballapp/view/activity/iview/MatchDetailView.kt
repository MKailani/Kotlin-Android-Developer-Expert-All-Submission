package com.one.submission.dicoding.myfootballapp.view.activity.iview

import com.one.submission.dicoding.myfootballapp.network.RepositoryCallbackApi
import com.one.submission.dicoding.myfootballapp.network.response.ResponseTeamFootball

/**
 * Dicoding Academy
 *
 * Final Project
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 10/02/19.
 */
interface MatchDetailView : RepositoryCallbackApi<ResponseTeamFootball?> {
    fun setupToolbar()
    fun loadView()
    fun showLoading()
    fun showMessage(isSelected: Boolean)
    fun dismissLoading()
    fun selectedIconFav(isSelected: Boolean)
    fun insertDataFromDb()
    fun removeDataFromDB()
}