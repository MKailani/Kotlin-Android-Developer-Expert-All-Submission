package com.one.submission.dicoding.myfootballapp.utils.extension

import android.view.View

/**
 * Dicoding Academy
 *
 * Submission 2
 * Kotlin Android Developer Expert (MADE)
 *
 * Created by kheys on 04/02/19.
 */
fun View.show(){
    visibility = View.VISIBLE
}


fun View.hide(){
    visibility = View.GONE
}

fun Boolean.setTrue(): Boolean{
    return true
}

fun Boolean.setFalse(): Boolean{
    return false
}