package com.one.submission.dicoding.myfootballapp.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.one.submission.dicoding.myfootballapp.R
import com.one.submission.dicoding.myfootballapp.view.adapter.pager.MatchViewPagerAdapter
import com.one.submission.dicoding.myfootballapp.view.fragment.iview.PagerView
import kotlinx.android.synthetic.main.fragment_match.*

/**
 * Dicoding Academy
 *
 * Submission 2
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 04/02/19.
 */
class MatchFragment : BaseFragment(), PagerView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Load View
        loadView()
    }

    override fun loadView() {
        val adapter = MatchViewPagerAdapter(
            childFragmentManager
        )
        setHasOptionsMenu(true)

        val listFragment = initialPager()
        adapter.populateFragment(listFragment)

        viewpager.adapter = adapter
        tabs.setupWithViewPager(viewpager)
    }

    override fun initialPager(): MatchViewPagerAdapter.ViewFragmentList {

        val fragmentList =
            mutableListOf<Fragment>(
                LastMatchFragment(),
                NextMatchFragment()
            )

        val titleList =
            mutableListOf(
                getString(R.string.last_match_fragment_title),
                getString(R.string.next_match_fragment_title)
            )

        return MatchViewPagerAdapter.ViewFragmentList(fragmentList,titleList)

    }

}