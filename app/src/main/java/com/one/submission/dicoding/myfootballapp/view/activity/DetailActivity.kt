package com.one.submission.dicoding.myfootballapp.view.activity

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.MenuItem
import com.one.submission.dicoding.myfootballapp.R
import com.one.submission.dicoding.myfootballapp.database.QueryHelper
import com.one.submission.dicoding.myfootballapp.database.database
import com.one.submission.dicoding.myfootballapp.model.Event
import com.one.submission.dicoding.myfootballapp.network.RepositoryApi
import com.one.submission.dicoding.myfootballapp.network.response.ResponseTeamFootball
import com.one.submission.dicoding.myfootballapp.presenter.activity.DetailPresenter
import com.one.submission.dicoding.myfootballapp.utils.Utils
import com.one.submission.dicoding.myfootballapp.utils.espresso.EspressoIdlingResource
import com.one.submission.dicoding.myfootballapp.utils.extension.hide
import com.one.submission.dicoding.myfootballapp.utils.extension.show
import com.one.submission.dicoding.myfootballapp.view.activity.iview.DetailView
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.top_toolbar.*
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.design.snackbar

/**
 * Dicoding Academy
 *
 * Submission 4
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 06/02/19.
 */
class DetailActivity : BaseActivity(), DetailView {

    private lateinit var mPresenter: DetailPresenter
    private var menuItemFav: Menu? = null
    private var isFavorite = false
    private lateinit var event: Event

    companion object {
        const val EXTRA_EVENT = "extra:event"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Initialize Presenter
        mPresenter = DetailPresenter(this, RepositoryApi(), applicationContext)

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
        event = intent.getParcelableExtra(EXTRA_EVENT)

        EspressoIdlingResource.increment()
        // Home
        mPresenter.doLoadImageTeam(event.idHomeTeam.toString(), DetailPresenter.TypeTeam.HOME)

        EspressoIdlingResource.increment()
        // Away
        mPresenter.doLoadImageTeam(event.idAwayTeam.toString(), DetailPresenter.TypeTeam.AWAY)

        //Load Favorite
        isFavorite = mPresenter.isFavorite(event.idEvent)

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu_favorite, menu)
        menuItemFav = menu
        setFavorite()
        return super.onCreateOptionsMenu(menu)
    }

    override fun selectedIconFav(isSelected: Boolean) {
        isFavorite = isSelected
        Utils.menuIconDrawable(applicationContext, menuItemFav?.getItem(0), isSelected)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                super.onBackPressed()
            }
            R.id.action_menu_favorite -> {
                mPresenter.doFavorite(isFavorite)
            }
        }
        return false
    }

    private fun setFavorite() {
        selectedIconFav(isFavorite)
    }

    override fun showMessage(isSelected: Boolean) {
        EspressoIdlingResource.increment()
        ll_wrapper.snackbar(if (isSelected) getString(R.string.add_favorite_message) else getString(R.string.remove_favorite_message))
            .addCallback(object : Snackbar.Callback() {
                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    super.onDismissed(transientBottomBar, event)
                    EspressoIdlingResource.decrement()
                }
            })
    }

    override fun insertDataFromDb() {
        this.database.use {
            insert(QueryHelper.FavoriteMatch.TABLE_NAME_STATIC, *Event.pairToValueFieldDb(event))
        }
    }

    override fun removeDataFromDB() {
        this.database.use {
            delete(
                QueryHelper.FavoriteMatch.TABLE_NAME_STATIC,
                Event.ID_EVENT + " = {id}",
                "id" to event.idEvent.toString()
            )
        }
    }

    override fun onDataLoaded(data: ResponseTeamFootball?) {
        EspressoIdlingResource.decrement()
        val teams = data?.teams
        val type = data?.type

        if (type == DetailPresenter.TypeTeam.HOME)

            iv_team_home?.let {
                teams?.get(0)?.strTeamBadge?.let { it1 -> Utils.loadImage(applicationContext, iv_team_home, it1) }
            }

        if (type == DetailPresenter.TypeTeam.AWAY)
            iv_team_away?.let {
                teams?.get(0)?.strTeamBadge?.let { it1 -> Utils.loadImage(applicationContext, iv_team_away, it1) }
            }
    }

    override fun onDataError() {
        ll_wrapper.snackbar(getString(R.string.data_error_message))
            .addCallback(object : Snackbar.Callback() {
                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    super.onDismissed(transientBottomBar, event)
                    EspressoIdlingResource.decrement()
                }
            })
    }

}