package com.one.submission.dicoding.myfootballapp.network

/**
 * Dicoding Academy
 *
 * Final Project
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 10/02/19.
 */
interface RepositoryCallbackApi<T> {
    fun onDataLoaded(data: T?)
    fun onDataError()
}