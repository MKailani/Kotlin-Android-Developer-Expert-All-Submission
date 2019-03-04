package com.one.submission.dicoding.myfootballapp.network.response

import com.google.gson.annotations.SerializedName
import com.one.submission.dicoding.myfootballapp.model.Event

/**
 * Dicoding Academy
 *
 * Submission 3
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 05/02/19.
 */
data class ResponseMatchFootball(
    @SerializedName("events")
    var events: MutableList<Event>
)