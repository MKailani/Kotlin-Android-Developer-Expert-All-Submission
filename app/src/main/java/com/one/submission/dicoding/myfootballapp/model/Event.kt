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
data class Event(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("idEvent")
    var idEvent: String?,
    @SerializedName("dateEvent")
    var dateEvent: String?,
    @SerializedName("strTime")
    val strTime: String?,
    @SerializedName("idHomeTeam")
    var idHomeTeam: String?,
    @SerializedName("strHomeTeam")
    var strHomeTeam: String?,
    @SerializedName("intHomeScore")
    var intHomeScore: String?,
    @SerializedName("strHomeFormation")
    var strHomeFormation: String?,
    @SerializedName("strHomeGoalDetails")
    var strHomeGoalDetails: String?,
    @SerializedName("intHomeShots")
    var intHomeShots: String?,
    @SerializedName("strHomeLineupGoalkeeper")
    var strHomeLineupGoalkeeper: String?,
    @SerializedName("strHomeLineupDefense")
    var strHomeLineupDefense: String?,
    @SerializedName("strHomeLineupMidfield")
    var strHomeLineupMidfield: String?,
    @SerializedName("strHomeLineupForward")
    var strHomeLineupForward: String?,
    @SerializedName("strHomeLineupSubstitutes")
    var strHomeLineupSubstitutes: String?,
    @SerializedName("idAwayTeam")
    var idAwayTeam: String?,
    @SerializedName("strAwayTeam")
    var strAwayTeam: String?,
    @SerializedName("intAwayScore")
    var intAwayScore: String?,
    @SerializedName("strAwayFormation")
    var strAwayFormation: String?,
    @SerializedName("strAwayGoalDetails")
    var strAwayGoalDetails: String?,
    @SerializedName("intAwayShots")
    var intAwayShots: String?,
    @SerializedName("strAwayLineupGoalkeeper")
    var strAwayLineupGoalkeeper: String?,
    @SerializedName("strAwayLineupDefense")
    var strAwayLineupDefense: String?,
    @SerializedName("strAwayLineupMidfield")
    var strAwayLineupMidfield: String?,
    @SerializedName("strAwayLineupForward")
    var strAwayLineupForward: String?,
    @SerializedName("strAwayLineupSubstitutes")
    var strAwayLineupSubstitutes: String?
) : Parcelable {
    // Favorite Sport
    companion object {
        const val ID = "ID"
        const val ID_EVENT = "ID_EVENT"
        const val DATE_EVENT = "DATE"
        const val TIME_EVENT = "TIME"
        const val HOME_ID = "HOME_ID"
        const val HOME_TEAM = "HOME_TEAM"
        const val HOME_SCORE = "HOME_SCORE"
        const val HOME_FORMATION = "HOME_FORMATION"
        const val HOME_GOAL_DETAILS = "HOME_GOAL_DETAILS"
        const val HOME_SHOTS = "HOME_SHOTS"
        const val HOME_LINEUP_GOALKEEPER = "HOME_LINEUP_GOALKEEPER"
        const val HOME_LINEUP_DEFENSE = "HOME_LINEUP_DEFENSE"
        const val HOME_LINEUP_MIDFIELD = "HOME_LINEUP_MIDFIELD"
        const val HOME_LINEUP_FORWARD = "HOME_LINEUP_FORWARD"
        const val HOME_LINEUP_SUBSTITUTES = "HOME_LINEUP_SUBSTITUTES"
        const val AWAY_ID = "AWAY_ID"
        const val AWAY_TEAM = "AWAY_TEAM"
        const val AWAY_SCORE = "AWAY_SCORE"
        const val AWAY_FORMATION = "AWAY_FORMATION"
        const val AWAY_GOAL_DETAILS = "AWAY_GOAL_DETAILS"
        const val AWAY_SHOTS = "AWAY_SHOTS"
        const val AWAY_LINEUP_GOALKEEPER = "AWAY_LINEUP_GOALKEEPER"
        const val AWAY_LINEUP_DEFENSE = "AWAY_LINEUP_DEFENSE"
        const val AWAY_LINEUP_MIDFIELD = "AWAY_LINEUP_MIDFIELD"
        const val AWAY_LINEUP_FORWARD = "AWAY_LINEUP_FORWARD"
        const val AWAY_LINEUP_SUBSTITUTES = "AWAY_LINEUP_SUBSTITUTES"

        fun pairToValueFieldDb(event: Event): Array<Pair<String, Any?>> = arrayOf(

            ID_EVENT to event.idEvent,
            DATE_EVENT to event.dateEvent,
            TIME_EVENT to event.strTime,
            HOME_ID to event.idHomeTeam,
            HOME_TEAM to event.strHomeTeam,
            HOME_SCORE to event.intHomeScore,
            HOME_FORMATION to event.strHomeFormation,
            HOME_GOAL_DETAILS to event.strHomeGoalDetails,
            HOME_SHOTS to event.intHomeScore,
            HOME_LINEUP_GOALKEEPER to event.strHomeLineupGoalkeeper,
            HOME_LINEUP_DEFENSE to event.strHomeLineupDefense,
            HOME_LINEUP_MIDFIELD to event.strHomeLineupMidfield,
            HOME_LINEUP_FORWARD to event.strHomeLineupForward,
            HOME_LINEUP_SUBSTITUTES to event.strHomeLineupSubstitutes,

            AWAY_ID to event.idAwayTeam,
            AWAY_TEAM to event.strAwayTeam,
            AWAY_SCORE to event.intAwayScore,
            AWAY_FORMATION to event.strAwayFormation,
            AWAY_GOAL_DETAILS to event.strAwayGoalDetails,
            AWAY_SHOTS to event.intAwayScore,
            AWAY_LINEUP_GOALKEEPER to event.strAwayLineupGoalkeeper,
            AWAY_LINEUP_DEFENSE to event.strAwayLineupDefense,
            AWAY_LINEUP_MIDFIELD to event.strAwayLineupMidfield,
            AWAY_LINEUP_FORWARD to event.strAwayLineupForward,
            AWAY_LINEUP_SUBSTITUTES to event.strAwayLineupSubstitutes
        )
    }
}








