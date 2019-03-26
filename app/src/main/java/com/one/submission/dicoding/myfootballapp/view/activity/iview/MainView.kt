package com.one.submission.dicoding.myfootballapp.view.activity.iview

import com.one.submission.dicoding.myfootballapp.model.Event
import com.one.submission.dicoding.myfootballapp.model.Player
import com.one.submission.dicoding.myfootballapp.model.Team

/**
 * Dicoding Academy
 *
 * Final Project
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 10/02/19.
 */
interface MainView {
    fun setupToolbar()
    fun setupListener()
    fun loadView()
    fun goToNextActivity(data: Event)
    fun goToNextActivity(data: Team)
    fun goToNextActivity(data: Player)
}