package com.one.submission.dicoding.myfootballapp.view.activity

import android.os.Bundle
import android.view.MenuItem
import com.one.submission.dicoding.myfootballapp.R
import com.one.submission.dicoding.myfootballapp.model.Event
import com.one.submission.dicoding.myfootballapp.model.Team
import com.one.submission.dicoding.myfootballapp.presenter.activity.DetailPresenter
import com.one.submission.dicoding.myfootballapp.utils.Utils
import com.one.submission.dicoding.myfootballapp.utils.extension.hide
import com.one.submission.dicoding.myfootballapp.utils.extension.show
import com.one.submission.dicoding.myfootballapp.view.activity.iview.DetailView
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.top_toolbar.*

/**
 * Dicoding Academy
 *
 * Submission 2
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 04/02/19.
 */
class DetailActivity : BaseActivity(), DetailView {

    private lateinit var mPresenter: DetailPresenter

    companion object {
        const val EXTRA_EVENT = "extra:event"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Initialize Presenter
        mPresenter = DetailPresenter(this)

        // Load View
        setupToolbar()
        loadView()

    }

    override fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.detail_title_toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun loadView() {

        // Get Ientent
        val event = intent.getParcelableExtra<Event>(EXTRA_EVENT)

        // Home
        mPresenter.doLoadImageTeam(event.idHomeTeam, DetailPresenter.TypeTeam.HOME)

        // Away
        mPresenter.doLoadImageTeam(event.idAwayTeam, DetailPresenter.TypeTeam.AWAY)


        // Extension
        tv_team_home.text = event.strHomeTeam ?: ""
        tv_team_away.text = event.strAwayTeam ?: ""
        tv_score_home.text = event.intHomeScore ?: ""
        tv_score_away.text = event.intAwayScore ?: ""
        tv_home_goal.text = event.strHomeGoalDetails ?: ""
        tv_away_goal.text = event.strAwayGoalDetails ?: ""
        tv_home_shoot.text = event.intHomeScore ?: ""
        tv_away_shoot.text = event.intAwayScore ?: ""
        tv_home_goalkeaper.text = event.strHomeLineupGoalkeeper ?: ""
        tv_away_goalkeaper.text = event.strAwayLineupGoalkeeper ?: ""
        tv_home_defense.text = event.strHomeLineupDefense ?: ""
        tv_away_defense.text = event.strAwayLineupDefense ?: ""
        tv_home_modifield.text = event.strHomeLineupMidfield ?: ""
        tv_away_modifield.text = event.strAwayLineupMidfield ?: ""
        tv_home_forward.text = event.strHomeLineupForward ?: ""
        tv_away_forward.text = event.strAwayLineupForward ?: ""
        tv_home_subtitutes.text = event.strHomeLineupSubstitutes ?: ""
        tv_away_subtitutes.text = event.strAwayLineupSubstitutes ?: ""
    }

    override fun showLoading() {
        pbLoading.show()
        scrollView.hide()
    }

    override fun dismissLoading() {
        pbLoading.hide()
        scrollView.show()
    }

    /* Load Image */
    override fun loadImage(type: DetailPresenter.TypeTeam, teams: List<Team>) {
        if (type == DetailPresenter.TypeTeam.HOME)

            iv_team_home?.let {
                Utils.loadImage(applicationContext,iv_team_home, teams[0].strTeamBadge)
            }

        if (type == DetailPresenter.TypeTeam.AWAY)
            iv_team_away?.let{
                Utils.loadImage(applicationContext,iv_team_away, teams[0].strTeamBadge)
            }
    }


override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
        android.R.id.home -> {
            super.onBackPressed()
        }
    }
    return false
}
}