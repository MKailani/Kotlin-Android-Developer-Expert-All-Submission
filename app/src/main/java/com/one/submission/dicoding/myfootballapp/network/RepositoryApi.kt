package com.one.submission.dicoding.myfootballapp.network

import com.one.submission.dicoding.myfootballapp.App
import com.one.submission.dicoding.myfootballapp.network.response.ResponseMatchFootball
import com.one.submission.dicoding.myfootballapp.network.response.ResponseTeamFootball
import org.jetbrains.anko.error
import org.jetbrains.anko.info
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Dicoding Academy
 *
 * Submission 4
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 06/02/19.
 */
class RepositoryApi {

    fun getLoadImageTeam(id: String, callbackApi: RepositoryCallbackApi<ResponseTeamFootball?>) {

        RestClient
            .createService(ApiService::class.java)
            .getTeamLeague(id)
            .enqueue(object : Callback<ResponseTeamFootball?> {

                override fun onResponse(
                    call: Call<ResponseTeamFootball?>?,
                    response: Response<ResponseTeamFootball?>?
                ) {
                    response?.let {
                        if (it.isSuccessful) {
                            App.INSTANCE?.info("Success Load Team ")
                            callbackApi.onDataLoaded(it.body())
                        } else {
                            App.INSTANCE?.info("Error Load Team ")
                            callbackApi.onDataError()
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseTeamFootball?>?, t: Throwable?) {
                    App.INSTANCE?.info("Error Load Team : ", t)
                    callbackApi.onDataError()
                }
            })

    }


    fun getLastMatch(id: String, callbackApi: RepositoryCallbackApi<ResponseMatchFootball?>) {
        RestClient
            .createService(ApiService::class.java)
            .getLastMatch(id)
            .enqueue(object : Callback<ResponseMatchFootball?> {
                override fun onFailure(call: Call<ResponseMatchFootball?>?, t: Throwable?) {
                    App.INSTANCE?.error("Error Last Match : ", t)
                    callbackApi.onDataError()
                }

                override fun onResponse(
                    call: Call<ResponseMatchFootball?>?,
                    response: Response<ResponseMatchFootball?>?
                ) {
                    response?.let {
                        if (it.isSuccessful) {
                            App.INSTANCE?.info("Success Loaded Last Match ")
                            callbackApi.onDataLoaded(it.body())
                        } else {
                            App.INSTANCE?.error("Error Last Match ")
                            callbackApi.onDataError()
                        }
                    }
                }

            })
    }

    fun getNextMatch(id: String, callbackApi: RepositoryCallbackApi<ResponseMatchFootball?>) {
        RestClient
            .createService(ApiService::class.java)
            .getNextMatch(id)
            .enqueue(object : Callback<ResponseMatchFootball?> {
                override fun onResponse(
                    call: Call<ResponseMatchFootball?>?,
                    response: Response<ResponseMatchFootball?>?
                ) {
                    response?.let {
                        if (it.isSuccessful) {
                            App.INSTANCE?.info("Success Loaded Next Match ")
                            callbackApi.onDataLoaded(it.body())
                        } else {
                            App.INSTANCE?.error("Error Next Match ")
                            callbackApi.onDataError()
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseMatchFootball?>?, t: Throwable?) {
                    App.INSTANCE?.error("Error Next Match : ", t)
                    callbackApi.onDataError()
                }

            })
    }

}