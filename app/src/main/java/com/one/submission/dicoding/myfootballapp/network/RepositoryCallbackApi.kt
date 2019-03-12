package com.one.submission.dicoding.myfootballapp.network

/**
 * Dicoding Academy
 *
 * Submission 4
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 06/02/19.
 */
interface RepositoryCallbackApi<T> {
    fun onDataLoaded(data: T?)
    fun onDataError()
}