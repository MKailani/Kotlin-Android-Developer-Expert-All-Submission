package com.one.submission.dicoding.myfootballapp.network

import com.one.submission.dicoding.myfootballapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Dicoding Academy
 *
 * Submission 4
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 06/02/19.
 */
object RestClient {

    private const val TIMEOUT_DEFAULT = 15
    private const val TIMEOUT_LONG = 120
    private const val BASE_URL = BuildConfig.BASE_URL + "/" + BuildConfig.API_KEY + "/"

    fun <T> createService(service: Class<T>): T {
        return iniRetrofit(false).create(service)
    }

    private fun iniRetrofit(longRequest: Boolean): Retrofit {
        var timeOut = TIMEOUT_DEFAULT

        if (longRequest) {
            timeOut = TIMEOUT_LONG
        }

        // Logging Service Success or not REST Client
        val interceptor = HttpLoggingInterceptor()
        with(interceptor) {
            level = HttpLoggingInterceptor.Level.BODY
        }

        // Set Inisialization Config OKHTTP for OKHttpClient
        val okClient = with(OkHttpClient.Builder()) {
            // Config OKHTTP
            readTimeout(timeOut.toLong(), TimeUnit.SECONDS)
            connectTimeout(timeOut.toLong(), TimeUnit.SECONDS)
            addInterceptor(interceptor)
            build()
        }

        // Retrofit Client
        return with(Retrofit.Builder())
        {
            baseUrl(BASE_URL)
            client(okClient)
            addConverterFactory(GsonConverterFactory.create())
            build()
        }
    }

}