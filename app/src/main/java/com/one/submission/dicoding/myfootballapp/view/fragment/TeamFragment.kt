package com.one.submission.dicoding.myfootballapp.view.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import com.one.submission.dicoding.myfootballapp.R
import com.one.submission.dicoding.myfootballapp.model.League
import com.one.submission.dicoding.myfootballapp.model.Team
import com.one.submission.dicoding.myfootballapp.network.RepositoryApi
import com.one.submission.dicoding.myfootballapp.network.response.ResponseLeagueFootball
import com.one.submission.dicoding.myfootballapp.network.response.ResponseTeamFootball
import com.one.submission.dicoding.myfootballapp.presenter.fragment.TeamLeaguePresenter
import com.one.submission.dicoding.myfootballapp.utils.espresso.EspressoIdlingResource
import com.one.submission.dicoding.myfootballapp.utils.extension.hide
import com.one.submission.dicoding.myfootballapp.utils.extension.show
import com.one.submission.dicoding.myfootballapp.view.activity.MainActivity
import com.one.submission.dicoding.myfootballapp.view.adapter.recycler.TeamAdapter
import com.one.submission.dicoding.myfootballapp.view.fragment.iview.TeamView
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
class TeamFragment : BaseFragment(), TeamView {

    private lateinit var presenter: TeamLeaguePresenter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: TeamAdapter
    private var isLoading = false
    private var page = 1
    private var counter = 1
    private var totalItemCount: Int = 0
    private var lastVisibleItem: Int = 0
    private var lastItemCounter = 0

    private lateinit var league: League

    companion object {
        val TAG: String = TeamFragment::class.java.simpleName

        fun newInstance(): TeamFragment {
            return TeamFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recycler_item_with_spinner, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = TeamLeaguePresenter(this, RepositoryApi())

        // Load Data
        setupRecycler()
        setupListener()
        loadData()
    }

    override fun setupRecycler() {

        setHasOptionsMenu(true)

        adapter = TeamAdapter(presenter)
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
//                    presenter.doLastMatch("4328")
                }
            }
        })
    }

    override fun loadData() {
        EspressoIdlingResource.increment()
        presenter.getAllLeague()
    }

    override fun showLoading() {
        pbLoading?.show()
    }

    override fun goToNextActivity(team: Team) {
        (activity as MainActivity).goToNextActivity(team)
    }


    override fun dismissLoading() {
        pbLoading?.hide()

    }

    override fun onDataError() {
        EspressoIdlingResource.decrement()
        (activity as MainActivity).handlingMessageError()
    }

    override fun onDataLoaded(data: ResponseTeamFootball?) {
        EspressoIdlingResource.decrement()

            if(adapter.itemCount > 0)
                adapter.clearList()

        if (data != null) {
            if(!data.teams.isNullOrEmpty())
                adapter.addList(data.teams.toMutableList())
        }

    }


    override fun loadSpinner(data: ResponseLeagueFootball?) {
        this.isVisible.let{
            if (data != null) {

                spTeam?.adapter = ArrayAdapter(activity!!.baseContext, android.R.layout.simple_spinner_item, data.leagues.toList())

                spTeam?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {}

                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        league = spTeam?.selectedItem as League
                        presenter.getAllTeam(league.strLeague.toString())
                    }
                }
            }
        }

    }



    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {

        inflater?.inflate(R.menu.option_menu_search_view, menu)

        val searchMenu = menu?.findItem(R.id.action_menu_search)
        val searchView = searchMenu?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.doSearchTeam(query.toString())
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query.toString().isEmpty()) {
                    llSpinnerWrapper.show()
                    presenter.getAllTeam(spTeam.selectedItem.toString())
                } else{
                    llSpinnerWrapper.hide()
                }

                return true
            }
        })

        super.onCreateOptionsMenu(menu, inflater)

    }

}

