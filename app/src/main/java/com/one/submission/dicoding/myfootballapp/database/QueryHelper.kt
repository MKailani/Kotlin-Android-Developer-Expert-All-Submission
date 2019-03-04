package com.one.submission.dicoding.myfootballapp.database

import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import android.util.Log
import com.one.submission.dicoding.myfootballapp.model.Event
import org.jetbrains.anko.db.*

/**
 * Dicoding Academy
 *
 * Submission 3
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 05/02/19.
 */
class QueryHelper {

    var favoriteMatch: FavoriteMatch

    init {
        favoriteMatch = FavoriteMatch()
    }

    class FavoriteMatch : BaseColumns {

        private val tableName: String = "FavoriteSport"
        private val tag = FavoriteMatch::class.java.simpleName

        companion object {
            const val TABLE_NAME_STATIC: String = "FavoriteSport"
        }

        private var tableSchema: Array<Pair<String, SqlType>> = arrayOf(
            Event.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Event.ID_EVENT to TEXT,
            Event.DATE_EVENT to TEXT,
            Event.HOME_ID to TEXT,
            Event.HOME_TEAM to TEXT,
            Event.HOME_SCORE to TEXT,
            Event.HOME_FORMATION to TEXT,
            Event.HOME_GOAL_DETAILS to TEXT,
            Event.HOME_SHOTS to TEXT,
            Event.HOME_LINEUP_GOALKEEPER to TEXT,
            Event.HOME_LINEUP_DEFENSE to TEXT,
            Event.HOME_LINEUP_MIDFIELD to TEXT,
            Event.HOME_LINEUP_FORWARD to TEXT,
            Event.HOME_LINEUP_SUBSTITUTES to TEXT,
            Event.AWAY_ID to TEXT,
            Event.AWAY_TEAM to TEXT,
            Event.AWAY_SCORE to TEXT,
            Event.AWAY_FORMATION to TEXT,
            Event.AWAY_GOAL_DETAILS to TEXT,
            Event.AWAY_SHOTS to TEXT,
            Event.AWAY_LINEUP_GOALKEEPER to TEXT,
            Event.AWAY_LINEUP_DEFENSE to TEXT,
            Event.AWAY_LINEUP_MIDFIELD to TEXT,
            Event.AWAY_LINEUP_FORWARD to TEXT,
            Event.AWAY_LINEUP_SUBSTITUTES to TEXT
        )

        internal fun createDb(db: SQLiteDatabase?) {
            try {
                db?.createTable(tableName, false, *tableSchema)
            } catch (ex: SQLException) {
                Log.e(tag, "Create Table : $ex")
            }

        }

        internal fun dropTable(db: SQLiteDatabase?) {
            try {
                db?.dropTable(tableName, true)
            } catch (ex: SQLException) {
                Log.e(tag, "Drop Table : $ex")
            }

        }

    }

}