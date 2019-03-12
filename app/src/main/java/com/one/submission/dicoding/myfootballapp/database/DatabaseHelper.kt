package com.one.submission.dicoding.myfootballapp.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper

/**
 * Dicoding Academy
 *
 * Submission 4
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 06/02/19.
 */
class DatabaseHelper(context: Context) : ManagedSQLiteOpenHelper(context, "SportFavorite.db", null, 1) {
    private var queryHelper: QueryHelper = QueryHelper()

    override fun onCreate(db: SQLiteDatabase?) {
        queryHelper.favoriteMatch.createDb(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        queryHelper.favoriteMatch.dropTable(db)
    }

    companion object {
        private var instance: DatabaseHelper? = null

        @Synchronized
        fun getInstance(context: Context): DatabaseHelper {
            if (instance == null) {
                instance = DatabaseHelper(context.applicationContext)
            }

            return instance as DatabaseHelper
        }
    }
}

val Context.database: DatabaseHelper
    get() = DatabaseHelper.getInstance(applicationContext)
