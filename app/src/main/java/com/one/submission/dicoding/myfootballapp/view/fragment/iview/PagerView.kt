package com.one.submission.dicoding.myfootballapp.view.fragment.iview

import com.one.submission.dicoding.myfootballapp.view.adapter.pager.ViewPagerAdapter

/**
 * Dicoding Academy
 *
 * Final Project
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 10/02/19.
 */
interface PagerView {
    fun initialPager() : ViewPagerAdapter.ViewFragmentList
    fun loadView()
}