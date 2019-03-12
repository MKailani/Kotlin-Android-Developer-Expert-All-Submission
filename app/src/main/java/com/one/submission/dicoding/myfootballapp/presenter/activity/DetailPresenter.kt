package com.one.submission.dicoding.myfootballapp.presenter.activity

import android.content.Context
import com.one.submission.dicoding.myfootballapp.database.QueryHelper.FavoriteMatch.Companion.TABLE_NAME_STATIC
import com.one.submission.dicoding.myfootballapp.database.database
import com.one.submission.dicoding.myfootballapp.model.Event
import com.one.submission.dicoding.myfootballapp.network.RepositoryApi
import com.one.submission.dicoding.myfootballapp.network.RepositoryCallbackApi
import com.one.submission.dicoding.myfootballapp.network.response.ResponseTeamFootball
import com.one.submission.dicoding.myfootballapp.view.activity.iview.DetailView
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 * Dicoding Academy
 *
 * Submission 4
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 05/02/19.
 */
class DetailPresenter(
    private var mView: DetailView,
    private val repositoryApi: RepositoryApi,
    private var context: Context
) {

    // Load Data Image Team
    fun doLoadImageTeam(id: String, type: TypeTeam) {
        mView.showLoading()
        repositoryApi.getLoadImageTeam(id, object : RepositoryCallbackApi<ResponseTeamFootball?> {
            override fun onDataLoaded(data: ResponseTeamFootball?) {
                data?.type = type
                mView.onDataLoaded(data)
            }

            override fun onDataError() {
                mView.onDataError()
            }

        })

        mView.dismissLoading()

    }

    fun doFavorite(isFavorite: Boolean) {
        if (isFavorite) {
            mView.selectedIconFav(!isFavorite)
            mView.removeDataFromDB()
            mView.showMessage(!isFavorite)

        } else {
            mView.selectedIconFav(!isFavorite)
            mView.insertDataFromDb()
            mView.showMessage(!isFavorite)
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