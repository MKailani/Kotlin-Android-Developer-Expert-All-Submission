package com.one.submission.dicoding.myfootballapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
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
data class Team(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("idTeam")
    val idTeam: String?,
    @SerializedName("strTeamBadge")
    val strTeamBadge: String?,
    @SerializedName("strTeam")
    val strTeam: String?,
    @SerializedName("intFormedYear")
    val intFormedYear: String?,
    @SerializedName("strStadium")
    val strStadium: String?,
    @SerializedName("strDescriptionEN")
    val strDescriptionEN: String?
): Parcelable {
    // Favorite Team Sport
    companion object {
        const val ID = "ID"
        const val ID_TEAM = "ID_TEAM"
        const val TEAM_BADGE = "TEAM_BADGE"
        const val TEAM = "TEAM"
        const val FORMED_YEAR = "FORMED_YEAR"
        const val STADIUM = "STADIUM"
        const val DESCRIPTION = "DESCRIPTION"

        fun pairToValueFieldDb(team: Team): Array<Pair<String, Any?>> = arrayOf(
            ID to team.id,
            ID_TEAM to team.idTeam,
            TEAM_BADGE to team.strTeamBadge,
            TEAM to team.strTeam,
            FORMED_YEAR to team.intFormedYear,
            STADIUM to team.strStadium,
            DESCRIPTION to team.strDescriptionEN
        )
    }
}