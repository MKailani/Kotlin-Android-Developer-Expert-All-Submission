package com.one.submission.dicoding.myfootballapp.view.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.Snackbar
import android.view.MenuItem
import com.one.submission.dicoding.myfootballapp.R
import com.one.submission.dicoding.myfootballapp.model.Event
import com.one.submission.dicoding.myfootballapp.model.Player
import com.one.submission.dicoding.myfootballapp.model.Team
import com.one.submission.dicoding.myfootballapp.utils.espresso.EspressoIdlingResource
import com.one.submission.dicoding.myfootballapp.view.activity.iview.MainView
import com.one.submission.dicoding.myfootballapp.view.fragment.*
import kotlinx.android.synthetic.main.bottom_toolbar.*
import kotlinx.android.synthetic.main.top_toolbar_with_content.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.intentFor

/**
 * Dicoding Academy
 *
 * Final Project
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 10/02/19.
 */
class MainActivity : BaseActivity(), MainView, BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        EspressoIdlingResource.increment()

        // Initialize
        setupToolbar()
        setupListener()

        //Selected First Fragment
        if (savedInstanceState == null)
            loadView()

    }

    override fun loadView() {
        onNavigationItemSelected(bottomNav.menu.getItem(0))
    }

    override fun setupListener() {
        // Toolbar Listener
        bottomNav.setOnNavigationItemSelectedListener(this)

        supportFragmentManager.addOnBackStackChangedListener {
            if (getCurrentFragment() != null) {
                when {
                    getCurrentFragment() is MatchFragment -> setCheckedToolbarBottom(R.id.bottom_prev_match)
                    getCurrentFragment() is TeamFragment -> setCheckedToolbarBottom(R.id.bottom_next_match)
                    getCurrentFragment() is FavoriteFragment -> setCheckedToolbarBottom(R.id.bottom_favorite)
                }
            }
        }

    }

    override fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.main_title_toolbar)
    }

    // Anko Common
    override fun goToNextActivity(data: Event) {
        EspressoIdlingResource.increment()
        startActivity(
            intentFor<MatchDetailActivity>(
                MatchDetailActivity.EXTRA_EVENT to data
            )
        )
    }

    override fun goToNextActivity(data: Team) {
        EspressoIdlingResource.increment()
        startActivity(
            intentFor<TeamsDetailActivity>(
                TeamsDetailActivity.EXTRA_TEAM to data
            )
        )
    }

    override fun goToNextActivity(data: Player) {
        EspressoIdlingResource.increment()
        startActivity(
            intentFor<Player>(
//                TeamPlayerFragment.EXTRA_TEAM to data
            )
        )
    }

    private fun setCheckedToolbarBottom(resId: Int) {
        var index = 0
        when (resId) {
            R.id.bottom_prev_match -> index = 0
            R.id.bottom_next_match -> index = 1
            R.id.bottom_favorite -> index = 2
        }

        bottomNav.menu.getItem(index).isChecked = true
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        setCheckedToolbarBottom(menuItem.itemId)
        // Idling Resource Espresso Increment
        EspressoIdlingResource.increment()
        when (menuItem.itemId) {
            R.id.bottom_prev_match -> {
                if(getCurrentFragment() !is MatchFragment){
                    val matchFragment = MatchFragment.newInstance()
                    replaceFragment(matchFragment, MatchFragment.TAG)
                }
            }
            R.id.bottom_next_match -> {
                if(getCurrentFragment() !is TeamFragment){
                    val teamFragment = TeamFragment.newInstance()
                    replaceFragment(teamFragment, TeamFragment.TAG)
                }
            }
            R.id.bottom_favorite -> {
                if(getCurrentFragment() !is FavoriteFragment){
                    val favoriteFragment = FavoriteFragment.newInstance()
                    replaceFragment(favoriteFragment, FavoriteFragment.TAG)
                }
            }
        }
        return false
    }

    fun handlingMessageError(){
        bottomNav.snackbar(getString(R.string.data_error_message))
            .addCallback(object : Snackbar.Callback() {
                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    super.onDismissed(transientBottomBar, event)
                    EspressoIdlingResource.decrement()
                }
            })
    }

}
