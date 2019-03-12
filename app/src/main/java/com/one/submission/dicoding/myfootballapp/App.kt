package com.one.submission.dicoding.myfootballapp

import android.app.Application
import org.jetbrains.anko.AnkoLogger

/**
 * Dicoding Academy
 *
 * Submission 4
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 06/02/19.
 */
class App : Application(), AnkoLogger {

    companion object {
        var INSTANCE: App? = App()
    }

}