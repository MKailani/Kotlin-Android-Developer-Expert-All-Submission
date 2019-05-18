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
data class Player(
    val strCutout: String?,
    val strPlayer: String?,
    val strPosition: String?,
    val strWeight: String?,
    val strHeight: String?,
    val strDescriptionEN: String?,
    val strFanart1: String?
): Parcelable