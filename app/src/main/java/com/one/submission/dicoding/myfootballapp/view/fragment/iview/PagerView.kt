package com.one.submission.dicoding.myfootballapp.view.fragment.iview

import com.one.submission.dicoding.myfootballapp.view.adapter.pager.MatchViewPagerAdapter

/**
 * Dicoding Academy
 *
 * Submission 2
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 04/02/19.
 */
interface PagerView {
    fun initialPager() : MatchViewPagerAdapter.ViewFragmentList
    fun loadView()
}