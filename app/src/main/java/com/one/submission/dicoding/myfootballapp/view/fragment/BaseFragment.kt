package com.one.submission.dicoding.myfootballapp.view.fragment

import android.support.v4.app.Fragment
import com.one.submission.dicoding.myfootballapp.utils.espresso.EspressoIdlingResource


/**
 * Dicoding Academy
 *
 * Final Project
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 10/02/19.
 */
abstract class BaseFragment : Fragment() {
    override fun onResume() {
        super.onResume()

        // Espresso Testing Idling Resource Decrement
        EspressoIdlingResource.decrement()
    }
}