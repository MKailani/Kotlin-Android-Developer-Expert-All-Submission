package com.one.submission.dicoding.myfootballapp.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.one.submission.dicoding.myfootballapp.R
import com.one.submission.dicoding.myfootballapp.view.fragment.iview.PagerView
import com.one.submission.dicoding.myfootballapp.view.adapter.pager.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_pager.*

/**
 * Dicoding Academy
 *
 * Final Project
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 10/02/19.
 */
class FavoriteFragment : BaseFragment(), PagerView {

    companion object {
        val TAG: String = FavoriteFragment::class.java.simpleName

        fun newInstance() : FavoriteFragment{
            return FavoriteFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Load View
        loadView()
    }

    override fun loadView() {
        val adapter = ViewPagerAdapter(
            childFragmentManager
        )
        setHasOptionsMenu(true)

        val listFragment = initialPager()
        adapter.populateFragment(listFragment)

        viewpager.adapter = adapter
        tabs.setupWithViewPager(viewpager)
    }

    override fun initialPager(): ViewPagerAdapter.ViewFragmentList {

        val fragmentList =
            mutableListOf<Fragment>(
                FavoriteMatchesFragment.newInstance(),
                FavoriteTeamFragment.newInstance()
            )

        val titleList =
            mutableListOf(
                getString(R.string.match_fragment_pager_title),
                getString(R.string.team_fragment_pager_title)
            )

        return ViewPagerAdapter.ViewFragmentList(fragmentList,titleList)

    }

}