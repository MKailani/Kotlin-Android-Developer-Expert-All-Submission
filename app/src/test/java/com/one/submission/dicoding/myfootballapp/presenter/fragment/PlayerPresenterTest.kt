package com.one.submission.dicoding.myfootballapp.presenter.fragment

import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.one.submission.dicoding.myfootballapp.network.RepositoryApi
import com.one.submission.dicoding.myfootballapp.network.RepositoryCallbackApi
import com.one.submission.dicoding.myfootballapp.network.response.ResponsePlayerFootball
import com.one.submission.dicoding.myfootballapp.view.fragment.iview.PlayerView
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
class PlayerPresenterTest {

    @Mock
    private lateinit var mView: PlayerView
    @Mock
    private lateinit var mPresenter: PlayerPresenter

    @Mock
    private lateinit var repositoryApi: RepositoryApi

    @Mock
    private lateinit var responsePlayerFootball: ResponsePlayerFootball


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mPresenter = PlayerPresenter(mView, repositoryApi)
    }

    @Test
    fun getPlayerLoadedTest() {
        val playerName = ""

        mPresenter.getPlayer(playerName)

        argumentCaptor<RepositoryCallbackApi<ResponsePlayerFootball?>>().apply {

            Mockito.verify(repositoryApi).getPlayer(eq(playerName), capture())
            firstValue.onDataLoaded(responsePlayerFootball)
        }

        Mockito.verify(mView).showLoading()
        Mockito.verify(mView).onDataLoaded(responsePlayerFootball)
        Mockito.verify(mView).dismissLoading()
    }

    @Test
    fun getPlayerErrorTest() {
        val playerName = ""

        mPresenter.getPlayer(playerName)

        argumentCaptor<RepositoryCallbackApi<ResponsePlayerFootball?>>().apply {

            Mockito.verify(repositoryApi).getPlayer(eq(playerName), capture())
            firstValue.onDataError()
        }

        Mockito.verify(mView).showLoading()
        Mockito.verify(mView).onDataError()
        Mockito.verify(mView).dismissLoading()
    }

}