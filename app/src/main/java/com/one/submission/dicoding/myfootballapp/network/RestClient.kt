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
 * Submission 3
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 05/02/19.
 */
class RestClient {

    companion object {
        private const val TIMEOUT_DEFAULT = 20
        private const val TIMEOUT_LONG = 120
        private const val BASE_URL = BuildConfig.BASE_URL +"/"+BuildConfig.API_KEY+"/"
    }

    private lateinit var apiService: ApiService

    constructor() : this(false)

    private constructor(longRequest: Boolean) {
        this(longRequest, BASE_URL)
    }

    private operator fun invoke(longRequest: Boolean, baseUrl: String) {
        var timeOut = TIMEOUT_DEFAULT

        if (longRequest) {
            timeOut = TIMEOUT_LONG
        }

        // Logging Service Success or not REST Client
        val interceptor = HttpLoggingInterceptor()
        with(interceptor){
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
        apiService = with(Retrofit.Builder())
                    {
                        baseUrl(baseUrl)
                        client(okClient)
                        addConverterFactory(GsonConverterFactory.create())
                        build()
                    }.create(ApiService::class.java)
    }

    fun getApiService(): ApiService = apiService

}