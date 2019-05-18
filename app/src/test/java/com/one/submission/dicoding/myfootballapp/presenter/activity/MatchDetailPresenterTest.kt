package com.one.submission.dicoding.myfootballapp.presenter.activity

import android.content.Context
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.one.submission.dicoding.myfootballapp.network.RepositoryApi
import com.one.submission.dicoding.myfootballapp.network.RepositoryCallbackApi
import com.one.submission.dicoding.myfootballapp.network.response.ResponseTeamFootball
import com.one.submission.dicoding.myfootballapp.view.activity.iview.MatchDetailView
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
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
class MatchDetailPresenterTest {

    @Mock
    private lateinit var mViewMatch: MatchDetailView

    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var repositoryApi: RepositoryApi

    @Mock
    private lateinit var mDetailPresenter: MatchDetailPresenter

    @Mock
    private lateinit var responseTeamFootball: ResponseTeamFootball

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mDetailPresenter = MatchDetailPresenter(mViewMatch, repositoryApi,context)
    }

    @Test
    fun doLoadImageTeamLoadedTest() {

        val id = "4328"

        mDetailPresenter.doLoadImageTeam(id, MatchDetailPresenter.TypeTeam.AWAY)

        argumentCaptor<RepositoryCallbackApi<ResponseTeamFootball?>>().apply {

            verify(repositoryApi).getLoadImageTeam(eq(id), capture())
            firstValue.onDataLoaded(responseTeamFootball)
        }

        Mockito.verify(mViewMatch).showLoading()
        Mockito.verify(mViewMatch).onDataLoaded(responseTeamFootball)
        Mockito.verify(mViewMatch).dismissLoading()

    }

    @Test
    fun doLoadImageTeamErrorTest() {

        val id = ""

        mDetailPresenter.doLoadImageTeam(id, MatchDetailPresenter.TypeTeam.AWAY)

        argumentCaptor<RepositoryCallbackApi<ResponseTeamFootball?>>().apply {

            verify(repositoryApi).getLoadImageTeam(eq(id), capture())
            firstValue.onDataError()
        }

        Mockito.verify(mViewMatch).showLoading()
        Mockito.verify(mViewMatch).onDataError()
        Mockito.verify(mViewMatch).dismissLoading()

    }

}