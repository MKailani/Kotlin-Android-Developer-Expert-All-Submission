package com.one.submission.dicoding.myfootballapp.view.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.one.submission.dicoding.myfootballapp.R
import com.one.submission.dicoding.myfootballapp.model.Team
import com.one.submission.dicoding.myfootballapp.network.response.ResponseLeagueFootball
import com.one.submission.dicoding.myfootballapp.network.response.ResponseTeamFootball
import com.one.submission.dicoding.myfootballapp.presenter.fragment.FavoriteTeamPresenter
import com.one.submission.dicoding.myfootballapp.utils.espresso.EspressoIdlingResource
import com.one.submission.dicoding.myfootballapp.utils.extension.hide
import com.one.submission.dicoding.myfootballapp.utils.extension.show
import com.one.submission.dicoding.myfootballapp.view.activity.MainActivity
import com.one.submission.dicoding.myfootballapp.view.adapter.recycler.TeamAdapter
import com.one.submission.dicoding.myfootballapp.view.fragment.iview.TeamView
import kotlinx.android.synthetic.main.fragment_recycler_item.*

/**
 * Dicoding Academy
 *
 * Final Project
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 10/02/19.
 */
class FavoriteTeamFragment : BaseFragment(), TeamView {

    override fun loadSpinner(data: ResponseLeagueFootball?) {}

    private lateinit var presenter: FavoriteTeamPresenter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: TeamAdapter

    companion object {

        fun newInstance(): FavoriteTeamFragment {
            return FavoriteTeamFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recycler_item, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Init Presenter
        presenter = FavoriteTeamPresenter(this, view.context)

    }

    override fun onResume() {
        super.onResume()

        EspressoIdlingResource.decrement()
        // Load Data
        setupRecycler()
        loadData()
    }

    override fun setupRecycler() {
        adapter = TeamAdapter(presenter)
        layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

    }

    override fun loadData() {
        EspressoIdlingResource.increment()
        presenter.doFavoriteTeam()
    }


    override fun showLoading() {
        pbLoading?.show()
    }

    override fun setupListener() {
        //TODO: if you need Listener Event
    }


    override fun dismissLoading() {
        pbLoading?.hide()
    }

    override fun onDataLoaded(data: ResponseTeamFootball?) {
        this.isVisible.let {
            EspressoIdlingResource.decrement()
            dismissLoading()
            val teams = data?.teams
            teams?.let {
                adapter.addList(it.toMutableList())
                if (it.isNotEmpty())
                    tvNoData.hide()
                else
                    tvNoData.show()
            }
        }
    }

    override fun onDataError() {
        EspressoIdlingResource.decrement()
        (activity as MainActivity).handlingMessageError()

    }

    override fun goToNextActivity(team: Team) {
        (activity as MainActivity).goToNextActivity(team)
    }

}
