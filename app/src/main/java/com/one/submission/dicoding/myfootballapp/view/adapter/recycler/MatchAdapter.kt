package com.one.submission.dicoding.myfootballapp.view.adapter.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.one.submission.dicoding.myfootballapp.R
import com.one.submission.dicoding.myfootballapp.model.Event
import com.one.submission.dicoding.myfootballapp.presenter.activity.MatchSearchPresenter
import com.one.submission.dicoding.myfootballapp.presenter.fragment.FavoriteMatchPresenter
import com.one.submission.dicoding.myfootballapp.presenter.fragment.LastMatchPresenter
import com.one.submission.dicoding.myfootballapp.presenter.fragment.NextMatchPresenter
import com.one.submission.dicoding.myfootballapp.utils.Utils
import com.one.submission.dicoding.myfootballapp.view.adapter.recycler.viewholder.ProgressViewHolder
import kotlinx.android.synthetic.main.row_item_match.view.*

/**
 * Dicoding Academy
 *
 * Final Project
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 10/02/19.
 */
class MatchAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // final static
    companion object {
        private const val VIEW_PROGRESS: Int = 0
        private const val VIEW_ITEM: Int = 1
    }

    var lastMatchPresenter: LastMatchPresenter? = null
    var nextMatchPresenter: NextMatchPresenter? = null
    var favoritePresenter: FavoriteMatchPresenter? = null
    var matchSearchPresenter: MatchSearchPresenter? = null

    var mDataset: MutableList<Event?> = ArrayList()

    constructor(lastMatchPresenter: LastMatchPresenter) : this() {
        this.lastMatchPresenter = lastMatchPresenter
    }

    constructor(nextMatchPresenter: NextMatchPresenter) : this() {
        this.nextMatchPresenter = nextMatchPresenter
    }

    constructor(favoritePresenter: FavoriteMatchPresenter) : this() {
        this.favoritePresenter = favoritePresenter
    }

    constructor(matchSearchPresenter: MatchSearchPresenter) : this() {
        this.matchSearchPresenter = matchSearchPresenter
    }


    fun addList(mDataset: MutableList<Event>) {
        this.mDataset.addAll(mDataset)
        this.notifyDataSetChanged()
    }

    fun clearList(){
        this.mDataset.clear()
        this.notifyDataSetChanged()
    }

    override fun getItemCount(): Int = mDataset.size

    override fun getItemViewType(position: Int): Int {
        return if (mDataset[position] != null) VIEW_ITEM else VIEW_PROGRESS
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        when (viewType) {
            VIEW_ITEM -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.row_item_match, parent, false)

                return MatchViewHolder(view)
            }

            VIEW_PROGRESS -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.row_item_progressbar, parent, false)
                return ProgressViewHolder(view)
            }
            else -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.row_item_progressbar, parent, false)
                return ProgressViewHolder(view)
            }

        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MatchViewHolder) {
            mDataset[position]?.let { holder.bind(it) }
        } else if (holder is ProgressViewHolder) {
            holder.progresBar.isIndeterminate = true
        }
    }


    inner class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Event) = with(itemView) {
            itemView.tvDate.text = Utils.getDateFormat(item.dateEvent)
            itemView.tvTime.text = Utils.getTimeFormat(item.strTime)
            itemView.tvHome.text = item.strHomeTeam
            itemView.tvHomeScore.text = item.intHomeScore
            itemView.tvAway.text = item.strAwayTeam
            itemView.tvAwayScore.text = item.intAwayScore

            setOnClickListener {
                mDataset[adapterPosition]?.let { data ->

                    lastMatchPresenter?.mView?.goToNextActivity(data)
                    nextMatchPresenter?.mView?.goToNextActivity(data)
                    favoritePresenter?.mView?.goToNextActivity(data)
                    matchSearchPresenter?.mView?.goToNextActivity(data)
                }

            }

        }

    }

}