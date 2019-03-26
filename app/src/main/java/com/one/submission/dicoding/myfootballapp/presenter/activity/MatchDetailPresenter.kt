package com.one.submission.dicoding.myfootballapp.presenter.activity

import android.content.Context
import com.one.submission.dicoding.myfootballapp.database.QueryHelper.FavoriteMatch.Companion.TABLE_NAME_STATIC
import com.one.submission.dicoding.myfootballapp.database.database
import com.one.submission.dicoding.myfootballapp.model.Event
import com.one.submission.dicoding.myfootballapp.network.RepositoryApi
import com.one.submission.dicoding.myfootballapp.network.RepositoryCallbackApi
import com.one.submission.dicoding.myfootballapp.network.response.ResponseTeamFootball
import com.one.submission.dicoding.myfootballapp.view.activity.iview.MatchDetailView
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
class MatchDetailPresenter(
    private var mViewMatch: MatchDetailView,
    private val repositoryApi: RepositoryApi,
    private var context: Context
) {

    // Load Data Image Team
    fun doLoadImageTeam(id: String, type: TypeTeam) {
        mViewMatch.showLoading()
        repositoryApi.getLoadImageTeam(id, object : RepositoryCallbackApi<ResponseTeamFootball?> {
            override fun onDataLoaded(data: ResponseTeamFootball?) {
                data?.type = type
                mViewMatch.onDataLoaded(data)
            }

            override fun onDataError() {
                mViewMatch.onDataError()
            }

        })

        mViewMatch.dismissLoading()

    }

    fun doFavorite(isFavorite: Boolean) {
        if (isFavorite) {
            mViewMatch.selectedIconFav(!isFavorite)
            mViewMatch.removeDataFromDB()
            mViewMatch.showMessage(!isFavorite)

        } else {
            mViewMatch.selectedIconFav(!isFavorite)
            mViewMatch.insertDataFromDb()
            mViewMatch.showMessage(!isFavorite)
        }

    }

    fun isFavorite(idEvent: String?): Boolean {
        var hasFavorite = false
        context.database.use {

            val favorites = select(TABLE_NAME_STATIC).whereArgs(
                Event.ID_EVENT + "= {id}",
                "id" to idEvent.toString()
            ).parseList(classParser<Event>())

            hasFavorite = !favorites.isEmpty()
        }

        return hasFavorite
    }

    enum class TypeTeam {
        HOME, AWAY
    }

}