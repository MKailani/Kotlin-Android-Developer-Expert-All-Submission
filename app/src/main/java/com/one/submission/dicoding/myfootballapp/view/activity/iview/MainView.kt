package com.one.submission.dicoding.myfootballapp.view.activity.iview

import com.one.submission.dicoding.myfootballapp.model.Event

/**
 * Dicoding Academy
 *
 * Submission 2
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 04/02/19.
 */
interface MainView {
    fun setupToolbar()
    fun loadView()
    fun goToNextActivity(data: Event)
}