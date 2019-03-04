package com.one.submission.dicoding.myfootballapp.view.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.one.submission.dicoding.myfootballapp.R
import com.one.submission.dicoding.myfootballapp.model.Event
import com.one.submission.dicoding.myfootballapp.presenter.fragment.FavoritePresenter
import com.one.submission.dicoding.myfootballapp.utils.extension.hide
import com.one.submission.dicoding.myfootballapp.utils.extension.show
import com.one.submission.dicoding.myfootballapp.view.activity.MainActivity
import com.one.submission.dicoding.myfootballapp.view.adapter.recycler.MatchAdapter
import com.one.submission.dicoding.myfootballapp.view.fragment.iview.CommonView
import kotlinx.android.synthetic.main.fragment_recycler_item.*

/**
 * Dicoding Academy
 *
 * Submission 3
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 05/02/19.
 */
class FavoriteFragment : BaseFragment(), CommonView {

    override fun goToNextActivity(event: Event) {

        (activity as MainActivity).goToNextActivity(event)
    }

    private lateinit var presenter: FavoritePresenter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var  adapter : MatchAdapter


    companion object {
        val TAG:String = FavoriteFragment::class.java.simpleName

        fun newInstance(): FavoriteFragment {
            return FavoriteFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recycler_item, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Init Presenter
        presenter = FavoritePresenter(this, view.context)

    }

    override fun onResume() {
        super.onResume()

        // Load Data
        setupRecycler()
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

    override fun loadData() {
        presenter.doFavoriteMatch()
    }


    override fun showLoading() {
        pbLoading?.show()
    }

    override fun setupListener() {
        //TODO: if you need Listener Event
    }


    override fun showData(listData: MutableList<Event>) {
        dismissLoading()
        adapter.addList(listData)
        if(listData.size > 0)
            tvNoData.hide()
        else
            tvNoData.show()
    }


    override fun dismissLoading() {
        pbLoading?.hide()

    }
}