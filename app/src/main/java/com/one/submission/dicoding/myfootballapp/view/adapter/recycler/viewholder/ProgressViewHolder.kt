package com.one.submission.dicoding.myfootballapp.view.adapter.recycler.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import com.one.submission.dicoding.myfootballapp.R

/**
 * Dicoding Academy
 *
 * Submission 2
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 04/02/19.
 */
class ProgressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val progresBar =itemView.findViewById(R.id.pbLoading) as ProgressBar
}