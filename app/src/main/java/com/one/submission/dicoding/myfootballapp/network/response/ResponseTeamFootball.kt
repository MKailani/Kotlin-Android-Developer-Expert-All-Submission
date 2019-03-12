package com.one.submission.dicoding.myfootballapp.network.response

import com.google.gson.annotations.SerializedName
import com.one.submission.dicoding.myfootballapp.model.Team
import com.one.submission.dicoding.myfootballapp.presenter.activity.DetailPresenter

/**
 * Dicoding Academy
 *
 * Submission 4
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 05/02/19.
 */
data class ResponseTeamFootball(
    @SerializedName("teams")
    var teams: List<Team>,
    var type: DetailPresenter.TypeTeam // Validate HOME / AWAY
)