package com.one.submission.dicoding.myfootballapp.network.response

import com.google.gson.annotations.SerializedName
import com.one.submission.dicoding.myfootballapp.model.League

/**
 * Dicoding Academy
 *
 * Final Project
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 10/02/19.
 */
data class ResponseLeagueFootball(
    @SerializedName("leagues")
    val leagues: MutableList<League>
)