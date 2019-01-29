package com.one.submission.dicoding.myfootballapp.presenter

import android.content.Context
import android.content.Intent
import com.one.submission.dicoding.myfootballapp.model.Football
import com.one.submission.dicoding.myfootballapp.view.activity.DetailActivity
import com.one.submission.dicoding.myfootballapp.view.activity.iview.MainView

/**
 * Created by kheys on 30/01/19.
 *
 */
class MainPresenter(val mView:MainView, val context: Context) {

    fun doNextActivity(data: Football){
        val nextIntent = Intent(context,DetailActivity::class.java)
        nextIntent.putExtra(DetailActivity.EXTRA_IMAGE_PATH,data.imagePath)
        nextIntent.putExtra(DetailActivity.EXTRA_TITLE,data.title)
        nextIntent.putExtra(DetailActivity.EXTRA_DESC,data.desc)
        mView.goToNextActivity(nextIntent)
    }
}