package com.one.submission.dicoding.myfootballapp.presenter.fragment

import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.one.submission.dicoding.myfootballapp.network.RepositoryApi
import com.one.submission.dicoding.myfootballapp.network.RepositoryCallbackApi
import com.one.submission.dicoding.myfootballapp.network.response.ResponseTeamFootball
import com.one.submission.dicoding.myfootballapp.view.fragment.iview.TeamView
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
class TeamLeaguePresenterTest {

    @Mock
    private lateinit var mView: TeamView
    @Mock
    private lateinit var mPresenter: TeamLeaguePresenter

    @Mock
    private lateinit var repositoryApi: RepositoryApi

    @Mock
    private lateinit var responseTeamFootball: ResponseTeamFootball


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mPresenter = TeamLeaguePresenter(mView, repositoryApi)
    }

    @Test
    fun getTeamLeagueLoadedTest() {
        val team = "English Premier League"

        mPresenter.getAllTeam(team)

        argumentCaptor<RepositoryCallbackApi<ResponseTeamFootball?>>().apply {

            Mockito.verify(repositoryApi).getAllTeam(eq(team), capture())
            firstValue.onDataLoaded(responseTeamFootball)
        }

        Mockito.verify(mView).showLoading()
        Mockito.verify(mView).onDataLoaded(responseTeamFootball)
        Mockito.verify(mView).dismissLoading()
    }

    @Test
    fun getTeamLeagueErrorTest() {
        val team = ""

        mPresenter.getAllTeam(team)

        argumentCaptor<RepositoryCallbackApi<ResponseTeamFootball?>>().apply {

            Mockito.verify(repositoryApi).getAllTeam(eq(team), capture())
            firstValue.onDataError()
        }

        Mockito.verify(mView).showLoading()
        Mockito.verify(mView).onDataError()
        Mockito.verify(mView).dismissLoading()
    }

}