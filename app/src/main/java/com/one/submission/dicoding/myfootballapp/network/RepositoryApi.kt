package com.one.submission.dicoding.myfootballapp.network

import com.one.submission.dicoding.myfootballapp.App
import com.one.submission.dicoding.myfootballapp.network.response.*
import org.jetbrains.anko.error
import org.jetbrains.anko.info
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Dicoding Academy
 *
 * Final Project
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 10/02/19.
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

    fun getLeague( callbackApi: RepositoryCallbackApi<ResponseLeagueFootball?>) {
        RestClient
            .createService(ApiService::class.java)
            .getAllLeague()
            .enqueue(object : Callback<ResponseLeagueFootball?> {
                override fun onResponse(
                    call: Call<ResponseLeagueFootball?>?,
                    response: Response<ResponseLeagueFootball?>?
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

                override fun onFailure(call: Call<ResponseLeagueFootball?>?, t: Throwable?) {
                    App.INSTANCE?.error("Error Next Match : ", t)
                    callbackApi.onDataError()
                }

            })
    }

    fun getPlayer(playerName: String, callbackApi: RepositoryCallbackApi<ResponsePlayerFootball?>) {
        RestClient
            .createService(ApiService::class.java)
            .getAllPlayer(playerName)
            .enqueue(object : Callback<ResponsePlayerFootball?> {
                override fun onResponse(
                    call: Call<ResponsePlayerFootball?>?,
                    response: Response<ResponsePlayerFootball?>?
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

                override fun onFailure(call: Call<ResponsePlayerFootball?>?, t: Throwable?) {
                    App.INSTANCE?.error("Error Next Match : ", t)
                    callbackApi.onDataError()
                }

            })
    }


    fun getMatchSearch(playerName: String, callbackApi: RepositoryCallbackApi<ResponseEventFootball?>) {
        RestClient
            .createService(ApiService::class.java)
            .getMatchSearch(playerName)
            .enqueue(object : Callback<ResponseEventFootball?> {
                override fun onResponse(
                    call: Call<ResponseEventFootball?>?,
                    response: Response<ResponseEventFootball?>?
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

                override fun onFailure(call: Call<ResponseEventFootball?>?, t: Throwable?) {
                    App.INSTANCE?.error("Error Next Match : ", t)
                    callbackApi.onDataError()
                }

            })
    }


    fun getTeamSearch(playerName: String, callbackApi: RepositoryCallbackApi<ResponseTeamFootball?>) {
        RestClient
            .createService(ApiService::class.java)
            .getTeamsearch(playerName)
            .enqueue(object : Callback<ResponseTeamFootball?> {
                override fun onResponse(
                    call: Call<ResponseTeamFootball?>?,
                    response: Response<ResponseTeamFootball?>?
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

                override fun onFailure(call: Call<ResponseTeamFootball?>?, t: Throwable?) {
                    App.INSTANCE?.error("Error Next Match : ", t)
                    callbackApi.onDataError()
                }

            })
    }

    fun getAllTeam(team: String, callbackApi: RepositoryCallbackApi<ResponseTeamFootball?>) {
        RestClient
            .createService(ApiService::class.java)
            .getAllTeam(team)
            .enqueue(object : Callback<ResponseTeamFootball?> {
                override fun onResponse(
                    call: Call<ResponseTeamFootball?>?,
                    response: Response<ResponseTeamFootball?>?
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

                override fun onFailure(call: Call<ResponseTeamFootball?>?, t: Throwable?) {
                    App.INSTANCE?.error("Error Next Match : ", t)
                    callbackApi.onDataError()
                }

            })
    }

}