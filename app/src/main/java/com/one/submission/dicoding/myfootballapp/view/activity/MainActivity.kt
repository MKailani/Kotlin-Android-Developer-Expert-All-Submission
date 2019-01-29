package com.one.submission.dicoding.myfootballapp.view.activity

import android.content.Intent
import android.os.Bundle
import com.one.submission.dicoding.myfootballapp.R
import com.one.submission.dicoding.myfootballapp.model.Football
import com.one.submission.dicoding.myfootballapp.presenter.MainPresenter
import com.one.submission.dicoding.myfootballapp.view.activity.iview.MainView
import com.one.submission.dicoding.myfootballapp.view.adapter.FootballAdapter
import com.one.submission.dicoding.myfootballapp.view.component.RecyclerItemUI
import org.jetbrains.anko.setContentView

class MainActivity : BaseActivity(),MainView {

    var listFootbal:MutableList<Football> = ArrayList()
    lateinit var presenter:MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = MainPresenter(this, this.applicationContext)

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

    override fun goToNextActivity(intent: Intent) {
        startActivity(intent)
    }

}
