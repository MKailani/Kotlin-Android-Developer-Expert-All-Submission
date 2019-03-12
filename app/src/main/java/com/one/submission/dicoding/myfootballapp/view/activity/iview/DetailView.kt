package com.one.submission.dicoding.myfootballapp.view.activity.iview

import com.one.submission.dicoding.myfootballapp.network.RepositoryCallbackApi
import com.one.submission.dicoding.myfootballapp.network.response.ResponseTeamFootball

/**
 * Dicoding Academy
 *
 * Submission 4
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 06/02/19.
 */
interface DetailView : RepositoryCallbackApi<ResponseTeamFootball?> {
    fun setupToolbar()
    fun loadView()
    fun showLoading()
    fun showMessage(isSelected: Boolean)
    fun dismissLoading()
    fun selectedIconFav(isSelected: Boolean)
    fun insertDataFromDb()
    fun removeDataFromDB()
}