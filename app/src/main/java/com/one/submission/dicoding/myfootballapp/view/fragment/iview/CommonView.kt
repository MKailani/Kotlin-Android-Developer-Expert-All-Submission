package com.one.submission.dicoding.myfootballapp.view.fragment.iview

import com.one.submission.dicoding.myfootballapp.model.Event
import com.one.submission.dicoding.myfootballapp.network.RepositoryCallbackApi
import com.one.submission.dicoding.myfootballapp.network.response.ResponseMatchFootball
import com.one.submission.dicoding.myfootballapp.view.BaseView

/**
 * Dicoding Academy
 *
 * Submission 4
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 06/02/19.
 */
interface CommonView : BaseView, RepositoryCallbackApi<ResponseMatchFootball?> {
    fun setupRecycler()
    fun setupListener()
    fun loadData()
    fun goToNextActivity(event: Event)
}