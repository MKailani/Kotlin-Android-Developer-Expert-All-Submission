package com.one.submission.dicoding.myfootballapp.view.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.one.submission.dicoding.myfootballapp.R
import com.one.submission.dicoding.myfootballapp.model.Event
import com.one.submission.dicoding.myfootballapp.presenter.fragment.NextMatchPresenter
import com.one.submission.dicoding.myfootballapp.utils.extension.hide
import com.one.submission.dicoding.myfootballapp.utils.extension.setFalse
import com.one.submission.dicoding.myfootballapp.utils.extension.setTrue
import com.one.submission.dicoding.myfootballapp.utils.extension.show
import com.one.submission.dicoding.myfootballapp.view.activity.MainActivity
import com.one.submission.dicoding.myfootballapp.view.adapter.recycler.MatchAdapter
import com.one.submission.dicoding.myfootballapp.view.fragment.iview.CommonView
import kotlinx.android.synthetic.main.fragment_recycler_item.*

/**
 * Dicoding Academy
 *
 * Submission 2
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 04/02/19.
 */
class NextMatchFragment : BaseFragment(), CommonView {
    override fun goToNextActivity(event: Event) {

        (activity as MainActivity).goToNextActivity(event)
    }

    private lateinit var presenter: NextMatchPresenter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var  adapter : MatchAdapter
    private var isLoading = false
    private var page = 1
    private var counter = 1
    private var totalItemCount: Int = 0
    private var lastVisibleItem: Int = 0
    private var lastItemCounter = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recycler_item, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = NextMatchPresenter(this)

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
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                totalItemCount = layoutManager.itemCount
                lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                val visibleThreshold = 1
                if (lastItemCounter > 19 && !isLoading && totalItemCount <= lastVisibleItem + visibleThreshold) {
                    page = counter
                    presenter.doNextMatch()
                }
            }
        })
    }

    override fun loadData() {
        presenter.doNextMatch()
    }


    override fun showData(listData: MutableList<Event>) {
        dismissLoading()
        adapter.addList(listData)
    }

    override fun showLoading() {
        isLoading.setTrue()
        pbLoading?.show()
    }


    override fun dismissLoading() {
        isLoading.setFalse()
        pbLoading?.hide()

    }
}
