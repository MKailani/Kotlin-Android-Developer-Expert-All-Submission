package com.one.submission.dicoding.myfootballapp.presenter

import com.one.submission.dicoding.myfootballapp.model.Football
import com.one.submission.dicoding.myfootballapp.view.activity.iview.MainView

/**
 * Dicoding Academy
 *
 * Submission 1
 * Kotlin Android Developer Expert (MADE)
 *
 * Created by kheys on 30/01/19.
 */
class MainPresenter(private val mView: MainView) {

    fun doNextActivity(data: Football){
        mView.goToNextActivity(data)
    }
}