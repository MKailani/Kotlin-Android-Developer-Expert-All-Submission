package com.one.submission.dicoding.myfootballapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Dicoding Academy
 *
 * Final Project
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 10/02/19.
 */
@Parcelize
data class League(
    val idLeague: String?,
    val strLeague: String?): Parcelable{

    override fun toString(): String {
        return strLeague.toString()
    }
}