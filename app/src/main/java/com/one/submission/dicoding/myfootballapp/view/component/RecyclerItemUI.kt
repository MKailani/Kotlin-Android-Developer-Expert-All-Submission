package com.one.submission.dicoding.myfootballapp.view.component

import android.app.Activity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.one.submission.dicoding.myfootballapp.R
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

/**
 * Dicoding Academy
 *
 * Submission 1
 * Kotlin Android Developer Expert (MADE)
 *
 * Created by kheys on 30/01/19.
 */
class RecyclerItemUI<T : RecyclerView.ViewHolder?>(val mAdapter : RecyclerView.Adapter<T>)
    : AnkoComponent<Activity> {
    override fun createView(ui: AnkoContext<Activity>) = with(ui) {
        relativeLayout {
            lparams(width = matchParent, height = matchParent)

            recyclerView {
                id = R.id.rv_listItem

                layoutManager = LinearLayoutManager(context)
                adapter = mAdapter
            }.lparams(matchParent, matchParent)

            textView {
                id = R.id.tv_no_data
            }.lparams(width = wrapContent, height = wrapContent)
        }
    }

}
