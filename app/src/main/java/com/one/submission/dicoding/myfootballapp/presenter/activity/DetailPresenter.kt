package com.one.submission.dicoding.myfootballapp.presenter.activity

import android.content.Context
import com.one.submission.dicoding.myfootballapp.App
import com.one.submission.dicoding.myfootballapp.R
import com.one.submission.dicoding.myfootballapp.database.QueryHelper.FavoriteMatch.Companion.TABLE_NAME_STATIC
import com.one.submission.dicoding.myfootballapp.database.database
import com.one.submission.dicoding.myfootballapp.model.Event
import com.one.submission.dicoding.myfootballapp.network.RestClient
import com.one.submission.dicoding.myfootballapp.network.response.ResponseTeamFootball
import com.one.submission.dicoding.myfootballapp.view.activity.iview.DetailView
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.error
import org.jetbrains.anko.info
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Dicoding Academy
 *
 * Submission 3
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 05/02/19.
 */
class DetailPresenter(val mView:DetailView, private val context: Context) {

    private var restClient : RestClient = RestClient()

    fun doLoadImageTeam(id: String, type:TypeTeam){
        mView.showLoading()

        restClient.getApiService().getTeamLeague(id).enqueue(object : Callback<ResponseTeamFootball> {

            override fun onResponse(call: Call<ResponseTeamFootball>, response: Response<ResponseTeamFootball>) {
                App.INSTANCE?.info("Success Load Icon Team League ")
                mView.dismissLoading()
                takeIf { response.isSuccessful }.apply {
                    val data = response.body()?.teams
                    data?.let { mView.loadImage(type,it)}
                }
            }

            override fun onFailure(call: Call<ResponseTeamFootball>, t: Throwable) {
                App.INSTANCE?.error ( "Failed Load Icon Team League ",t )
                mView.dismissLoading()
            }
        })

    }

    fun doFavorite(isFavorite:Boolean, event: Event){
        if (isFavorite) {
            mView.selectedIconFav(!isFavorite)

            context.database.use{
                delete(
                    TABLE_NAME_STATIC,
                    Event.ID_EVENT + " = {id}",
                    "id" to event.idEvent.toString())
            }

            mView.showMessage(context.getString(R.string.remove_favorite_message))

        }else{
            mView.selectedIconFav(!isFavorite)

            context.database.use {
                insert(TABLE_NAME_STATIC, *Event.pairToValueFieldDb(event))
            }

            mView.showMessage(context.getString(R.string.add_favorite_message))
        }

    }

    fun isFavorite(idEvent:String?) : Boolean {
        var hasFavorite = false
        context.database.use {

            val favorites = select(TABLE_NAME_STATIC).whereArgs(
                Event.ID_EVENT +"= {id}",
                "id" to idEvent.toString()
            ).parseList(classParser<Event>())

            hasFavorite = !favorites.isEmpty()
        }

        return hasFavorite
    }

    enum class TypeTeam{
        HOME,AWAY
    }
}