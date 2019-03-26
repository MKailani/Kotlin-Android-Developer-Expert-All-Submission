package com.one.submission.dicoding.myfootballapp.view.activity

import android.os.Bundle
import android.view.MenuItem
import com.one.submission.dicoding.myfootballapp.R
import com.one.submission.dicoding.myfootballapp.model.Player
import com.one.submission.dicoding.myfootballapp.utils.Utils
import com.one.submission.dicoding.myfootballapp.view.activity.iview.PlayerDetailView
import kotlinx.android.synthetic.main.activity_players_detail.*
import kotlinx.android.synthetic.main.top_toolbar.*

class PlayersDetailActivity : BaseActivity(), PlayerDetailView {

    companion object {
        const val EXTRA_PLAYER = "extra:player"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_players_detail)

        setupToolbar()
        loadView()

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                super.onBackPressed()
            }
        }
        return false
    }

    override fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.player_title_toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun loadView() {

        val player = intent.getParcelableExtra<Player>(EXTRA_PLAYER)

        player.strCutout?.let { Utils.loadImage(applicationContext,ivPlayer, it) }

        tvWeight.text = player.strWeight

        var heightStr = "No Height"
        player.strHeight?.let{
            heightStr = it
        }

        var positionStr = "No Position"
        player.strPosition?.let {
            positionStr = it
        }

        tvHeight.text = heightStr
        tvPosition.text = positionStr
        tvDescription.text = player.strDescriptionEN
    }
}
