package com.one.submission.dicoding.myfootballapp.view.activity.iview

import com.one.submission.dicoding.myfootballapp.view.adapter.pager.ViewPagerAdapter

/**
 * Dicoding Academy
 *
 * Final Project
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 10/02/19.
 */
interface TeamDetailView{
    fun setupToolbar()
    fun loadView()
    fun showLoading()
    fun showMessage(isSelected: Boolean)
    fun dismissLoading()
    fun selectedIconFav(isSelected: Boolean)
    fun insertDataFromDb()
    fun removeDataFromDB()
    fun initPager() : ViewPagerAdapter.ViewFragmentList
}