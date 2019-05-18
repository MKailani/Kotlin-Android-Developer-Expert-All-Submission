package com.one.submission.dicoding.myfootballapp.view.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.one.submission.dicoding.myfootballapp.R
import com.one.submission.dicoding.myfootballapp.model.Event
import com.one.submission.dicoding.myfootballapp.model.League
import com.one.submission.dicoding.myfootballapp.network.RepositoryApi
import com.one.submission.dicoding.myfootballapp.network.response.ResponseLeagueFootball
import com.one.submission.dicoding.myfootballapp.network.response.ResponseMatchFootball
import com.one.submission.dicoding.myfootballapp.presenter.fragment.LastMatchPresenter
import com.one.submission.dicoding.myfootballapp.utils.espresso.EspressoIdlingResource
import com.one.submission.dicoding.myfootballapp.utils.extension.hide
import com.one.submission.dicoding.myfootballapp.utils.extension.show
import com.one.submission.dicoding.myfootballapp.view.activity.MainActivity
import com.one.submission.dicoding.myfootballapp.view.adapter.recycler.MatchAdapter
import com.one.submission.dicoding.myfootballapp.view.fragment.iview.CommonView
import kotlinx.android.synthetic.main.fragment_recycler_item.*
import kotlinx.android.synthetic.main.fragment_recycler_item_with_spinner.*

/**
 * Dicoding Academy
 *
 * Final Project
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 10/02/19.
 */
class LastMatchFragment : BaseFragment(), CommonView {


    private lateinit var presenter: LastMatchPresenter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: MatchAdapter
    private var isLoading = false
    private var page = 1
    private var counter = 1
    private var totalItemCount: Int = 0
    private var lastVisibleItem: Int = 0
    private var lastItemCounter = 0

    private lateinit var league: League

    companion object {

        fun newInstance(): LastMatchFragment {
            return LastMatchFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recycler_item_with_spinner, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = LastMatchPresenter(this, RepositoryApi())

        // Load Data
        setupRecycler()
        setupListener()
        loadData()
    }

    override fun setupRecycler() {

        adapter = MatchAdapter(presenter)
        layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

    }

    override fun setupListener() {

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                totalItemCount = layoutManager.itemCount
                lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                val visibleThreshold = 1
                if (lastItemCounter > 19 && !isLoading && totalItemCount <= lastVisibleItem + visibleThreshold) {
                    page = counter
                    presenter.doLastMatch("4328")
                }
            }
        })
    }

    override fun loadData() {
        EspressoIdlingResource.increment()
        presenter.getAllLeague()
        presenter.doLastMatch("4328")
    }

    override fun showLoading() {
        pbLoading?.show()
    }

    override fun goToNextActivity(event: Event) {
        (activity as MainActivity).goToNextActivity(event)
    }


    override fun dismissLoading() {
        pbLoading?.hide()

    }

    override fun onDataError() {
        EspressoIdlingResource.decrement()
        (activity as MainActivity).handlingMessageError()
    }

    override fun onDataLoaded(data: ResponseMatchFootball?) {
        EspressoIdlingResource.decrement()
        if (data != null) {

            if(adapter.itemCount > 0)
                adapter.clearList()

            adapter.addList(data.events)
        }
    }

    override fun loadSpinner(data: ResponseLeagueFootball?) {
        if (data != null) {

            spTeam?.adapter = ArrayAdapter(context!!, android.R.layout.simple_spinner_item, data.leagues.toList())

            spTeam?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    league = spTeam.selectedItem as League
                    presenter.doLastMatch(league.idLeague.toString())
                }
            }
        }
    }

}

