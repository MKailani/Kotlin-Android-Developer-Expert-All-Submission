package com.one.submission.dicoding.myfootballapp.presenter.activity

import com.one.submission.dicoding.myfootballapp.App
import com.one.submission.dicoding.myfootballapp.network.RestClient
import com.one.submission.dicoding.myfootballapp.network.response.ResponseTeamFootball
import com.one.submission.dicoding.myfootballapp.view.activity.iview.DetailView
import org.jetbrains.anko.error
import org.jetbrains.anko.info
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Dicoding Academy
 *
 * Submission 2
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 04/02/19.
 */
class DetailPresenter(val mView:DetailView) {

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

    enum class TypeTeam{
        HOME,AWAY
    }
}