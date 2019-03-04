package com.one.submission.dicoding.myfootballapp.view.fragment.iview

import com.one.submission.dicoding.myfootballapp.model.Event
import com.one.submission.dicoding.myfootballapp.view.BaseView

/**
 * Dicoding Academy
 *
 * Submission 3
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 05/02/19.
 */
interface CommonView : BaseView {
    fun setupRecycler()
    fun setupListener()
    fun loadData()
    fun showData(listData: MutableList<Event>)
    fun goToNextActivity(event: Event)
}