package com.one.submission.dicoding.myfootballapp.presenter.activity

import android.content.Context
import com.one.submission.dicoding.myfootballapp.database.QueryHelper
import com.one.submission.dicoding.myfootballapp.database.database
import com.one.submission.dicoding.myfootballapp.model.Team
import com.one.submission.dicoding.myfootballapp.view.activity.iview.TeamDetailView
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
class TeamDetailPresenter(
    private var mViewTeam: TeamDetailView,
    private var context: Context
) {

    fun doFavorite(isFavorite: Boolean) {
        if (isFavorite) {
            mViewTeam.selectedIconFav(!isFavorite)
            mViewTeam.removeDataFromDB()
            mViewTeam.showMessage(!isFavorite)

        } else {
            mViewTeam.selectedIconFav(!isFavorite)
            mViewTeam.insertDataFromDb()
            mViewTeam.showMessage(!isFavorite)
        }

    }

    fun isFavorite(idEvent: String?): Boolean {
        var hasFavorite = false
        context.database.use {

            val favorites = select(QueryHelper.FavoriteTeam.TABLE_NAME_STATIC).whereArgs(
                Team.ID_TEAM + "= {id}",
                "id" to idEvent.toString()
            ).parseList(classParser<Team>())

            hasFavorite = !favorites.isEmpty()
        }

        return hasFavorite
    }

}