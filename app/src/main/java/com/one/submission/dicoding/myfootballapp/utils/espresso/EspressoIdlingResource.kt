package com.one.submission.dicoding.myfootballapp.utils.espresso

import android.support.test.espresso.IdlingResource
import android.support.test.espresso.idling.CountingIdlingResource


/**
 * Dicoding Academy
 *
 * Submission 4
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 06/02/19.
 */
internal object EspressoIdlingResource {
    private const val RESOURCE = "GLOBAL"
    private val countingIdlingResource = CountingIdlingResource(RESOURCE)

    val idlingResource: IdlingResource
        get() = countingIdlingResource

    fun increment() {
        countingIdlingResource.increment()
    }

    fun decrement() {
        if (!idlingResource.isIdleNow)
            countingIdlingResource.decrement()
    }

}