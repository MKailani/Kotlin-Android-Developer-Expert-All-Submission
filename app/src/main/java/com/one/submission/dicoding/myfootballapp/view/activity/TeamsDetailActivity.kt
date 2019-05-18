package com.one.submission.dicoding.myfootballapp.view.activity

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import com.one.submission.dicoding.myfootballapp.R
import com.one.submission.dicoding.myfootballapp.database.QueryHelper
import com.one.submission.dicoding.myfootballapp.database.database
import com.one.submission.dicoding.myfootballapp.model.Team
import com.one.submission.dicoding.myfootballapp.presenter.activity.TeamDetailPresenter
import com.one.submission.dicoding.myfootballapp.utils.Utils
import com.one.submission.dicoding.myfootballapp.utils.espresso.EspressoIdlingResource
import com.one.submission.dicoding.myfootballapp.utils.extension.hide
import com.one.submission.dicoding.myfootballapp.utils.extension.show
import com.one.submission.dicoding.myfootballapp.view.activity.iview.TeamDetailView
import com.one.submission.dicoding.myfootballapp.view.adapter.pager.ViewPagerAdapter
import com.one.submission.dicoding.myfootballapp.view.fragment.TeamOverviewFragment
import com.one.submission.dicoding.myfootballapp.view.fragment.TeamPlayerFragment
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_teams_detail.*
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.design.snackbar

class TeamsDetailActivity : BaseActivity(), TeamDetailView {


    private lateinit var mDetailPresenter: TeamDetailPresenter
    private var menuItemFav: Menu? = null
    private var isFavorite = false
    private lateinit var team: Team

    companion object {
        const val EXTRA_TEAM = "extra:team"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teams_detail)

        // Initialize Presenter
        mDetailPresenter = TeamDetailPresenter(this, applicationContext)

        // Load View
        setupToolbar()
        loadView()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu_favorite, menu)
        menuItemFav = menu
        setFavorite()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                super.onBackPressed()
            }
            R.id.action_menu_favorite -> {
                EspressoIdlingResource.increment()
                mDetailPresenter.doFavorite(isFavorite)
            }
        }
        return false
    }

    override fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun loadView() {
        // Get Ientent
        EspressoIdlingResource.increment()
        team = intent.getParcelableExtra(TeamsDetailActivity.EXTRA_TEAM)

        team.strTeamBadge?.let { Utils.loadImage(applicationContext, ivTeam, it) }

        //Load Favorite
        isFavorite = mDetailPresenter.isFavorite(team.idTeam)

        tvName.text = team.strTeam
        tvYear.text = team.intFormedYear
        tvStadium.text = team.strStadium


        EspressoIdlingResource.increment()

        val adapter = ViewPagerAdapter(supportFragmentManager)

        val listFragment = initPager()
        adapter.populateFragment(listFragment)

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        EspressoIdlingResource.decrement()

    }

    override fun showLoading() {
        pbLoading.show()
        scrollView.hide()
    }

    override fun showMessage(isSelected: Boolean) {
        cl_wrapper.snackbar(if (isSelected) getString(R.string.add_favorite_message) else getString(R.string.remove_favorite_message))
            .addCallback(object : Snackbar.Callback() {
                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    super.onDismissed(transientBottomBar, event)
                    EspressoIdlingResource.decrement()
                }
            })
    }

    override fun dismissLoading() {
        pbLoading.hide()
        scrollView.show()
    }

    override fun selectedIconFav(isSelected: Boolean) {
        isFavorite = isSelected
        Utils.menuIconDrawable(applicationContext, menuItemFav?.getItem(0), isSelected)
    }

    override fun insertDataFromDb() {
        this.database.use {
            insert(QueryHelper.FavoriteTeam.TABLE_NAME_STATIC, *Team.pairToValueFieldDb(team))
        }
    }

    override fun removeDataFromDB() {
        this.database.use {
            delete(
                QueryHelper.FavoriteTeam.TABLE_NAME_STATIC,
                Team.ID_TEAM + " = {id}",
                "id" to team.idTeam.toString()
            )
        }
    }

    private fun setFavorite() {
        selectedIconFav(isFavorite)
    }


    override fun initPager(): ViewPagerAdapter.ViewFragmentList {
        val fragmentList =
            mutableListOf<Fragment>(
                TeamOverviewFragment.newInstance(team.strDescriptionEN.toString()),
                TeamPlayerFragment.newInstance(team.strTeam.toString())
            )

        val titleList =
            mutableListOf(
                getString(R.string.overview_fragment_pager_title),
                getString(R.string.player_fragment_pager_title)
            )

        return ViewPagerAdapter.ViewFragmentList(fragmentList, titleList)
    }

}
