package com.one.submission.dicoding.myfootballapp.presenter.fragment

import com.one.submission.dicoding.myfootballapp.App
import com.one.submission.dicoding.myfootballapp.network.RestClient
import com.one.submission.dicoding.myfootballapp.network.response.ResponseMatchFootball
import com.one.submission.dicoding.myfootballapp.view.fragment.iview.CommonView
import org.jetbrains.anko.error
import org.jetbrains.anko.info
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Dicoding Academy
 *
 * Submission 3
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 05/02/19.
 */
class NextMatchPresenter(val mView: CommonView) {
    private var restClient : RestClient = RestClient()
    fun doNextMatch(){
        mView.showLoading()
        restClient.getApiService().getNextMatch("4328").enqueue(object : Callback<ResponseMatchFootball> {
            override fun onResponse(call: Call<ResponseMatchFootball>, response: Response<ResponseMatchFootball>) {
                App.INSTANCE?.info("Success Load Last Match ")
                mView.dismissLoading()
                response.takeIf { response.isSuccessful }.apply {
                    val data = response.body()?.events
                    data?.let { mView.showData(it)}
                }
            }

            override fun onFailure(call: Call<ResponseMatchFootball>, t: Throwable) {
                App.INSTANCE?.error ("Failed Load Icon Team League " ,t)
                mView.dismissLoading()
            }
        })
    }
}

