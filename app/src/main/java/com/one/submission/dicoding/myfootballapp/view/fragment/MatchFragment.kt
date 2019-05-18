package com.one.submission.dicoding.myfootballapp.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.one.submission.dicoding.myfootballapp.R
import com.one.submission.dicoding.myfootballapp.view.activity.MatchSearchActivity
import com.one.submission.dicoding.myfootballapp.view.fragment.iview.PagerView
import com.one.submission.dicoding.myfootballapp.view.adapter.pager.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_pager.*
import org.jetbrains.anko.startActivity

/**
 * Dicoding Academy
 *
 * Final Project
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 10/02/19.
 */
class MatchFragment : BaseFragment(), PagerView {

    companion object {
        val TAG: String = NextMatchFragment::class.java.simpleName

        fun newInstance() : MatchFragment{
            return MatchFragment()
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
                LastMatchFragment.newInstance(),
                NextMatchFragment.newInstance()
            )

        val titleList =
            mutableListOf(
                getString(R.string.last_match_fragment_pager_title),
                getString(R.string.next_match_fragment_pager_title)
            )

        return ViewPagerAdapter.ViewFragmentList(fragmentList,titleList)

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater?.inflate(R.menu.option_menu_search, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_menu_search -> {
                context?.startActivity<MatchSearchActivity>()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}