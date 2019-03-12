package com.one.submission.dicoding.myfootballapp.presenter.activity

import android.content.Context
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.one.submission.dicoding.myfootballapp.network.RepositoryApi
import com.one.submission.dicoding.myfootballapp.network.RepositoryCallbackApi
import com.one.submission.dicoding.myfootballapp.network.response.ResponseTeamFootball
import com.one.submission.dicoding.myfootballapp.view.activity.iview.DetailView
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
 * Submission 4
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 06/02/19.
 */
@RunWith(MockitoJUnitRunner::class)
class DetailPresenterTest {

    @Mock
    private lateinit var mView: DetailView

    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var repositoryApi: RepositoryApi

    @Mock
    private lateinit var mPresenter: DetailPresenter

    @Mock
    private lateinit var responseTeamFootball: ResponseTeamFootball

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mPresenter = DetailPresenter(mView, repositoryApi,context)
    }

    @Test
    fun doLoadImageTeamLoadedTest() {

        val id = "4328"

        mPresenter.doLoadImageTeam(id, DetailPresenter.TypeTeam.AWAY)

        argumentCaptor<RepositoryCallbackApi<ResponseTeamFootball?>>().apply {

            verify(repositoryApi).getLoadImageTeam(eq(id), capture())
            firstValue.onDataLoaded(responseTeamFootball)
        }

        Mockito.verify(mView).showLoading()
        Mockito.verify(mView).onDataLoaded(responseTeamFootball)
        Mockito.verify(mView).dismissLoading()

    }

    @Test
    fun doLoadImageTeamErrorTest() {

        val id = ""

        mPresenter.doLoadImageTeam(id, DetailPresenter.TypeTeam.AWAY)

        argumentCaptor<RepositoryCallbackApi<ResponseTeamFootball?>>().apply {

            verify(repositoryApi).getLoadImageTeam(eq(id), capture())
            firstValue.onDataError()
        }

        Mockito.verify(mView).showLoading()
        Mockito.verify(mView).onDataError()
        Mockito.verify(mView).dismissLoading()

    }

}