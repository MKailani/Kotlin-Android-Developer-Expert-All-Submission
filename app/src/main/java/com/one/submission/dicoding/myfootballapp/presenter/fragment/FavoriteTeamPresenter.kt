package com.one.submission.dicoding.myfootballapp.presenter.fragment

import android.content.Context
import android.database.SQLException
import com.one.submission.dicoding.myfootballapp.database.QueryHelper.FavoriteTeam.Companion.TABLE_NAME_STATIC
import com.one.submission.dicoding.myfootballapp.database.database
import com.one.submission.dicoding.myfootballapp.model.Team
import com.one.submission.dicoding.myfootballapp.network.response.ResponseTeamFootball
import com.one.submission.dicoding.myfootballapp.view.fragment.iview.TeamView
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 * Dicoding Academy
 *
 * Final Project
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 10/02/19.
 */
class FavoriteTeamPresenter(val mView: TeamView,val context: Context) {

    // Load Data Favorite Match
    fun doFavoriteTeam() {
        mView.showLoading()
        context.database.use {
            try {
                mView.dismissLoading()
                val favorites = select(
                    TABLE_NAME_STATIC
                ).parseList(classParser<Team>())

                val response = ResponseTeamFootball(favorites.toList())
                mView.onDataLoaded(response)
            } catch (ex: SQLException) {
                mView.dismissLoading()
                mView.onDataError()
                ex.printStackTrace()
            }
        }

    }

}

