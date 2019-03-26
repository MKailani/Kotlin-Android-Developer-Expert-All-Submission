package com.one.submission.dicoding.myfootballapp.network.response

import com.google.gson.annotations.SerializedName
import com.one.submission.dicoding.myfootballapp.model.Team
import com.one.submission.dicoding.myfootballapp.presenter.activity.MatchDetailPresenter

/**
 * Dicoding Academy
 *
 * Final Project
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 10/02/19.
 */
data class ResponseTeamFootball(
    @SerializedName("teams")
    var teams: List<Team>,
    var type: MatchDetailPresenter.TypeTeam  = MatchDetailPresenter.TypeTeam.AWAY// Validate HOME / AWAY
)