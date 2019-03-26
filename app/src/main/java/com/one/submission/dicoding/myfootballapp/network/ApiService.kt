package com.one.submission.dicoding.myfootballapp.network

import com.one.submission.dicoding.myfootballapp.network.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Dicoding Academy
 *
 * Final Project
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 10/02/19.
 */
interface ApiService {
    @GET("eventsnextleague.php")
    fun getNextMatch(@Query("id") id: String): Call<ResponseMatchFootball>

    @GET("eventspastleague.php")
    fun getLastMatch(@Query("id") id: String): Call<ResponseMatchFootball>

    @GET("lookupteam.php")
    fun getTeamLeague(@Query("id") id: String): Call<ResponseTeamFootball>

    @GET("searchevents.php")
    fun getMatchSearch(@Query("e") event: String): Call<ResponseEventFootball>

    @GET("all_leagues.php")
    fun getAllLeague(): Call<ResponseLeagueFootball>

    @GET("searchplayers.php")
    fun getAllPlayer(@Query("t") playerName: String) :Call<ResponsePlayerFootball>

    @GET("searchteams.php")
    fun getTeamsearch(@Query("t") teamName: String) :Call<ResponseTeamFootball>

    @GET("search_all_teams.php")
    fun getAllTeam(@Query("l") leagueName: String) :Call<ResponseTeamFootball>


}