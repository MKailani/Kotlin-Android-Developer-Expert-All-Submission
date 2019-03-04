package com.one.submission.dicoding.myfootballapp.view.activity

import android.os.Bundle
import com.one.submission.dicoding.myfootballapp.R
import com.one.submission.dicoding.myfootballapp.model.Event
import com.one.submission.dicoding.myfootballapp.view.activity.iview.MainView
import com.one.submission.dicoding.myfootballapp.view.fragment.MatchFragment
import kotlinx.android.synthetic.main.top_toolbar_with_content.*
import org.jetbrains.anko.intentFor

/**
 * Dicoding Academy
 *
 * Submission 2
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 04/02/19.
 */
class MainActivity : BaseActivity(), MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize
        setupToolbar()
        loadView()
    }

    override fun loadView() {
        replaceFragment(MatchFragment())
    }

    override fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.main_title_toolbar)
    }

    // Anko Common
    override fun goToNextActivity(data: Event) {
        startActivity(intentFor<DetailActivity>(
            DetailActivity.EXTRA_EVENT to data
        ))
    }

}
