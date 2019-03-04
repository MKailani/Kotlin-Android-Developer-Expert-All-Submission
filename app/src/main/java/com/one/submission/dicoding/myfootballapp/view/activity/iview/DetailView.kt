package com.one.submission.dicoding.myfootballapp.view.activity.iview

import com.one.submission.dicoding.myfootballapp.model.Team
import com.one.submission.dicoding.myfootballapp.presenter.activity.DetailPresenter

/**
 * Dicoding Academy
 *
 * Submission 2
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 04/02/19.
 */
interface DetailView {
    fun setupToolbar()
    fun loadView()
    fun loadImage(type: DetailPresenter.TypeTeam, teams:List<Team>)
    fun showLoading()
    fun dismissLoading()
}