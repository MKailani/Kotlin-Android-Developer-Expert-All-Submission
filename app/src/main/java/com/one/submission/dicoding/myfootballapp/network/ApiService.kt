package com.one.submission.dicoding.myfootballapp.network

import com.one.submission.dicoding.myfootballapp.network.response.ResponseMatchFootball
import com.one.submission.dicoding.myfootballapp.network.response.ResponseTeamFootball
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Dicoding Academy
 *
 * Submission 4
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 06/02/19.
 */
interface ApiService {
    @GET("eventsnextleague.php")
    fun getNextMatch(@Query("id") id: String): Call<ResponseMatchFootball>

    @GET("eventspastleague.php")
    fun getLastMatch(@Query("id") id: String): Call<ResponseMatchFootball>

    @GET("lookupteam.php")
    fun getTeamLeague(@Query("id") id: String): Call<ResponseTeamFootball>
}