package com.one.submission.dicoding.myfootballapp.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.one.submission.dicoding.myfootballapp.R
import com.one.submission.dicoding.myfootballapp.model.Football
import com.one.submission.dicoding.myfootballapp.presenter.MainPresenter
import com.one.submission.dicoding.myfootballapp.utils.Utils
import com.one.submission.dicoding.myfootballapp.view.component.ItemFootballUI
import org.jetbrains.anko.AnkoContext


/**
 * Dicoding Academy
 *
 * Submission 1
 * Kotlin Android Developer Expert (MADE)
 *
 * Created by kheys on 30/01/19.
 */
class FootballAdapter(private val mPresenter: MainPresenter) : RecyclerView.Adapter<FootballAdapter.ViewHolder>() {

     var mDataset: MutableList<Football> = ArrayList()

    fun addList(mDataset: MutableList<Football>) {
        this.mDataset.addAll(mDataset)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFootballUI().createView(AnkoContext.create(parent.context)), mPresenter
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mDataset[position])
    }

    override fun getItemCount(): Int = mDataset.size


    inner class ViewHolder(itemView: View?, private val mPresenter: MainPresenter) : RecyclerView.ViewHolder(itemView) {
        private val ivFootbal: ImageView = itemView?.findViewById(R.id.iv_icon) as ImageView
        private val tvTitle: TextView = itemView?.findViewById(R.id.tv_title) as TextView

        fun bind(data: Football) {
            // Load Image
            Utils.loadImage(ivFootbal, data.imagePath)
            // Load Title
            tvTitle.text = data.title

            // Click Layout
            itemView.setOnClickListener {
                mPresenter.doNextActivity(mDataset[adapterPosition])
            }
        }
    }


}