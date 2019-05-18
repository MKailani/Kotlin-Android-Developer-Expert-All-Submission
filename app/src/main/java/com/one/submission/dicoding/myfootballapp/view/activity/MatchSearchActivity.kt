package com.one.submission.dicoding.myfootballapp.view.activity

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.widget.SearchView
import com.one.submission.dicoding.myfootballapp.R
import com.one.submission.dicoding.myfootballapp.model.Event
import com.one.submission.dicoding.myfootballapp.network.RepositoryApi
import com.one.submission.dicoding.myfootballapp.network.response.ResponseMatchFootball
import com.one.submission.dicoding.myfootballapp.presenter.activity.MatchSearchPresenter
import com.one.submission.dicoding.myfootballapp.utils.espresso.EspressoIdlingResource
import com.one.submission.dicoding.myfootballapp.utils.extension.hide
import com.one.submission.dicoding.myfootballapp.utils.extension.show
import com.one.submission.dicoding.myfootballapp.view.activity.iview.SearchIView
import com.one.submission.dicoding.myfootballapp.view.adapter.recycler.MatchAdapter
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_recycler_item.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.intentFor

class MatchSearchActivity : BaseActivity(),SearchIView {


    private lateinit var presenter: MatchSearchPresenter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: MatchAdapter
    private var isLoading = false
    private var page = 1
    private var counter = 1
    private var totalItemCount: Int = 0
    private var lastVisibleItem: Int = 0
    private var lastItemCounter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        presenter = MatchSearchPresenter(this, RepositoryApi())

        setupToolbar()
        setupRecycler()
        setupListener()
        loadData()
    }

    override fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.main_title_toolbar)
    }

    override fun setupRecycler() {
        adapter = MatchAdapter(presenter)
        layoutManager = LinearLayoutManager(
            applicationContext,
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
                    presenter.doSearch()
                }
            }
        })
    }

    override fun loadData() {
        EspressoIdlingResource.increment()
        presenter.doSearch()
    }

    override fun showLoading() {
        pbLoading?.show()
    }


    override fun dismissLoading() {
        pbLoading?.hide()

    }

    override fun onDataError() {
        EspressoIdlingResource.decrement()
        rl_wrapper.snackbar(getString(R.string.data_error_message))
            .addCallback(object : Snackbar.Callback() {
                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    super.onDismissed(transientBottomBar, event)
                    EspressoIdlingResource.decrement()
                }
            })
    }

    override fun onDataLoaded(data: ResponseMatchFootball?) {
        EspressoIdlingResource.decrement()
        if (data != null) {

            if(adapter.itemCount > 0)
                adapter.clearList()
            if(!data.events.isNullOrEmpty())
                adapter.addList(data.events)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.option_menu_search_view, menu)

        val searchMenu = menu?.findItem(R.id.action_menu_search)
        val searchView = searchMenu?.actionView as SearchView

        searchView.isIconified = false

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.doSearch(query.toString())

                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query.toString().isEmpty())
                    presenter.doSearch(query.toString())

                return true
            }
        })

        return super.onCreateOptionsMenu(menu)

    }

    override fun goToNextActivity(event: Event) {
        EspressoIdlingResource.increment()
        startActivity(
            intentFor<MatchDetailActivity>(
                MatchDetailActivity.EXTRA_EVENT to event
            )
        )
    }

}