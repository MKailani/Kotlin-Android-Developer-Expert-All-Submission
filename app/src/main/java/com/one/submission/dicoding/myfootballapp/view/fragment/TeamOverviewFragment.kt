package com.one.submission.dicoding.myfootballapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.one.submission.dicoding.myfootballapp.R
import kotlinx.android.synthetic.main.fragment_team_overview.*

/**
 * Dicoding Academy
 *
 * Final Project
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 10/02/19.
 */
class TeamOverviewFragment : BaseFragment() {

    companion object {
        private const val EXTRA_OVERVIEW = "extra:overview"

        fun newInstance(args: String): TeamOverviewFragment {
            val fragment = TeamOverviewFragment()
            val bundle = Bundle()
            bundle.putString(EXTRA_OVERVIEW,args)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_team_overview, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvOverview.text = arguments?.getString(EXTRA_OVERVIEW)
    }
}