package com.one.submission.dicoding.myfootballapp.view.activity.iview

import android.content.Intent

/**
 * Created by kheys on 30/01/19.
 *
 */
interface MainView {
    fun prepareData()
    fun loadView()
    fun goToNextActivity(intent: Intent)
}