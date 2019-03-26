package com.one.submission.dicoding.myfootballapp.view.fragment.iview

import com.one.submission.dicoding.myfootballapp.model.Player
import com.one.submission.dicoding.myfootballapp.network.RepositoryCallbackApi
import com.one.submission.dicoding.myfootballapp.network.response.ResponsePlayerFootball
import com.one.submission.dicoding.myfootballapp.view.BaseView

/**
 * Dicoding Academy
 *
 * Final Project
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 10/02/19.
 */
interface PlayerView : BaseView, RepositoryCallbackApi<ResponsePlayerFootball?> {
    fun setupRecycler()
    fun setupListener()
    fun loadData()
    fun goToNextActivity(player: Player)

}