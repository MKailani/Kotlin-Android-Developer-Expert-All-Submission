package com.one.submission.dicoding.myfootballapp.view.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.one.submission.dicoding.myfootballapp.R
import com.one.submission.dicoding.myfootballapp.model.Player
import com.one.submission.dicoding.myfootballapp.network.RepositoryApi
import com.one.submission.dicoding.myfootballapp.network.response.ResponsePlayerFootball
import com.one.submission.dicoding.myfootballapp.presenter.fragment.PlayerPresenter
import com.one.submission.dicoding.myfootballapp.utils.espresso.EspressoIdlingResource
import com.one.submission.dicoding.myfootballapp.utils.extension.hide
import com.one.submission.dicoding.myfootballapp.utils.extension.show
import com.one.submission.dicoding.myfootballapp.view.activity.MainActivity
import com.one.submission.dicoding.myfootballapp.view.activity.PlayersDetailActivity
import com.one.submission.dicoding.myfootballapp.view.adapter.recycler.PlayerAdapter
import com.one.submission.dicoding.myfootballapp.view.fragment.iview.PlayerView
import kotlinx.android.synthetic.main.fragment_recycler_item.*
import org.jetbrains.anko.support.v4.intentFor

/**
 * Dicoding Academy
 *
 * Final Project
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 10/02/19.
 */
class TeamPlayerFragment : BaseFragment(), PlayerView {

    private lateinit var presenter: PlayerPresenter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: PlayerAdapter

    companion object {
        private const val EXTRA_PLAYER = "extra:overview"

        fun newInstance(args: String): TeamPlayerFragment {
            val fragment = TeamPlayerFragment()
            val bundle = Bundle()
            bundle.putString(TeamPlayerFragment.EXTRA_PLAYER,args)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recycler_item, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Init Presenter
        presenter = PlayerPresenter(this, RepositoryApi())

    }

    override fun onResume() {
        super.onResume()

        EspressoIdlingResource.decrement()
        // Load Data
        setupRecycler()
        loadData()

    }

    override fun setupRecycler() {
        adapter = PlayerAdapter(presenter)
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
        arguments?.getString(EXTRA_PLAYER)?.let { presenter.getPlayer(it) }
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

    override fun onDataLoaded(data: ResponsePlayerFootball?) {
        this.isVisible.let {
            EspressoIdlingResource.decrement()
            dismissLoading()
            val teams = data?.player
            teams?.let {
                adapter.addList(it.toMutableList())
                if (it.size > 0)
                    tvNoData?.hide()
                else
                    tvNoData?.show()
            }
        }

    }

    override fun onDataError() {
        EspressoIdlingResource.decrement()
        (activity as MainActivity).handlingMessageError()

    }

    override fun goToNextActivity(player: Player) {
        activity?.startActivity(intentFor<PlayersDetailActivity>(
                PlayersDetailActivity.EXTRA_PLAYER to player
        ))
    }
}
