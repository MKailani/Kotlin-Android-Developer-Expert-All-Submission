package com.one.submission.dicoding.myfootballapp.view.activity.iview

import com.one.submission.dicoding.myfootballapp.model.Event

/**
 * Dicoding Academy
 *
 * Submission 3
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 05/02/19.
 */
interface MainView {
    fun setupToolbar()
    fun setupListener()
    fun loadView()
    fun goToNextActivity(data: Event)
}