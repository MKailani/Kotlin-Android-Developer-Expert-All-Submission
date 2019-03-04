package com.one.submission.dicoding.myfootballapp.presenter.fragment

import android.content.Context
import android.database.SQLException
import com.one.submission.dicoding.myfootballapp.database.QueryHelper.FavoriteMatch.Companion.TABLE_NAME_STATIC
import com.one.submission.dicoding.myfootballapp.database.database
import com.one.submission.dicoding.myfootballapp.model.Event
import com.one.submission.dicoding.myfootballapp.view.fragment.iview.CommonView
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 * Dicoding Academy
 *
 * Submission 3
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 05/02/19.
 */
class FavoritePresenter(val mView: CommonView, private val context: Context) {

    fun doFavoriteMatch(){
        mView.showLoading()
        context.database.use{
            try{
                mView.dismissLoading()
                val favorites = select(
                    TABLE_NAME_STATIC
                ).parseList(classParser<Event>())


                mView.showData(favorites.toMutableList())
            }catch (ex: SQLException){
                mView.dismissLoading()
                ex.printStackTrace()
            }
        }

    }

}

