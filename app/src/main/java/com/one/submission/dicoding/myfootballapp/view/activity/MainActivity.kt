package com.one.submission.dicoding.myfootballapp.view.activity

import android.os.Bundle
import com.one.submission.dicoding.myfootballapp.R
import com.one.submission.dicoding.myfootballapp.model.Football
import com.one.submission.dicoding.myfootballapp.presenter.MainPresenter
import com.one.submission.dicoding.myfootballapp.view.activity.iview.MainView
import com.one.submission.dicoding.myfootballapp.view.adapter.FootballAdapter
import com.one.submission.dicoding.myfootballapp.view.component.RecyclerItemUI
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.setContentView

/**
 * Dicoding Academy
 *
 * Submission 1
 * Kotlin Android Developer Expert (MADE)
 *
 * Created by kheys on 30/01/19.
 */
class MainActivity : BaseActivity(), MainView {

    private var listFootbal:MutableList<Football> = ArrayList()
    private lateinit var presenter:MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = MainPresenter(this)

        // Initialize
        prepareData()
        loadView()
    }

    override fun prepareData() {
        val desc = resources.getStringArray(R.array.club_description)
        val name = resources.getStringArray(R.array.club_name)
        val image = resources.obtainTypedArray(R.array.club_image)

        if(listFootbal.size > 0)
            listFootbal.clear()

        for (i in name.indices) {
            listFootbal.add(
                    Football(image.getResourceId(i, 0),
                    name[i],
                    desc[i])
            )
        }

        image.recycle()
    }

    override fun loadView() {
        val adapter = FootballAdapter(presenter)
        adapter.addList(listFootbal)
        RecyclerItemUI(adapter).setContentView(this)
    }

    // Anko Common
    override fun goToNextActivity(data: Football) {
        startActivity(intentFor<DetailActivity>(
            DetailActivity.EXTRA_IMAGE_PATH to data.imagePath,
            DetailActivity.EXTRA_TITLE to data.title,
            DetailActivity.EXTRA_DESC to data.desc
        ))
    }

}
