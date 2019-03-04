package com.one.submission.dicoding.myfootballapp.view.adapter.pager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Dicoding Academy
 *
 * Submission 2
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 04/02/19.
 */
class MatchViewPagerAdapter(mFragmentManager: FragmentManager?) : FragmentPagerAdapter(mFragmentManager){

    private lateinit var viewFragmentList: ViewFragmentList

    fun populateFragment(viewFragmentList: ViewFragmentList){
        this.viewFragmentList = viewFragmentList
    }
    override fun getItem(position: Int): Fragment? = viewFragmentList.mFragmentList[position]

    override fun getCount() = viewFragmentList.mFragmentList.size

    override fun getPageTitle(position: Int) = viewFragmentList.mTitleList[position]

    class ViewFragmentList(
        val mFragmentList: MutableList<Fragment>,
        val mTitleList:MutableList<String>)
}