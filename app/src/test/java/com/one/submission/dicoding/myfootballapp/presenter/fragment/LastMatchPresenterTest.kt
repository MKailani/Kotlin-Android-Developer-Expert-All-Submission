package com.one.submission.dicoding.myfootballapp.presenter.fragment

import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.one.submission.dicoding.myfootballapp.network.RepositoryApi
import com.one.submission.dicoding.myfootballapp.network.RepositoryCallbackApi
import com.one.submission.dicoding.myfootballapp.network.response.ResponseMatchFootball
import com.one.submission.dicoding.myfootballapp.view.fragment.iview.CommonView
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Dicoding Academy
 *
 * Final Project
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 10/02/19.
 */
@RunWith(MockitoJUnitRunner::class)
class LastMatchPresenterTest {

    @Mock
    private lateinit var mView: CommonView
    @Mock
    private lateinit var mPresenter: LastMatchPresenter

    @Mock
    private lateinit var repositoryApi: RepositoryApi

    @Mock
    private lateinit var responseMatchFootball: ResponseMatchFootball


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mPresenter = LastMatchPresenter(mView, repositoryApi)
    }

    @Test
    fun doLastMatchLoadedTest() {
        val id = "4328"

        mPresenter.doLastMatch(id)

        argumentCaptor<RepositoryCallbackApi<ResponseMatchFootball?>>().apply {

            Mockito.verify(repositoryApi).getLastMatch(eq(id), capture())
            firstValue.onDataLoaded(responseMatchFootball)
        }

        Mockito.verify(mView).showLoading()
        Mockito.verify(mView).onDataLoaded(responseMatchFootball)
        Mockito.verify(mView).dismissLoading()
    }

    @Test
    fun doLastMatchErrorTest() {

        val id = ""

        mPresenter.doLastMatch(id)

        argumentCaptor<RepositoryCallbackApi<ResponseMatchFootball?>>().apply {

            Mockito.verify(repositoryApi).getLastMatch(eq(id), capture())
            firstValue.onDataError()
        }

        Mockito.verify(mView).showLoading()
        Mockito.verify(mView).onDataError()
        Mockito.verify(mView).dismissLoading()
    }

}