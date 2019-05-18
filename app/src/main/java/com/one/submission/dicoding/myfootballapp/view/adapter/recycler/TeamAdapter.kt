package com.one.submission.dicoding.myfootballapp.view.adapter.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.one.submission.dicoding.myfootballapp.R
import com.one.submission.dicoding.myfootballapp.model.Team
import com.one.submission.dicoding.myfootballapp.presenter.fragment.FavoriteTeamPresenter
import com.one.submission.dicoding.myfootballapp.presenter.fragment.TeamLeaguePresenter
import com.one.submission.dicoding.myfootballapp.utils.Utils
import com.one.submission.dicoding.myfootballapp.view.adapter.recycler.viewholder.ProgressViewHolder
import kotlinx.android.synthetic.main.row_item_team.view.*

/**
 * Dicoding Academy
 *
 * Final Project
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 10/02/19.
 */
class TeamAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // final static
    companion object {
        private const val VIEW_PROGRESS: Int = 0
        private const val VIEW_ITEM: Int = 1
    }

    var teamLeaguePresenter: TeamLeaguePresenter? = null
    var favoriteTeamPresenter: FavoriteTeamPresenter? = null

    var mDataset: MutableList<Team?> = ArrayList()

    constructor(teamLeaguePresenter: TeamLeaguePresenter) : this() {
        this.teamLeaguePresenter = teamLeaguePresenter
    }

    constructor(favoritePresenter: FavoriteTeamPresenter) : this() {
        this.favoriteTeamPresenter = favoritePresenter
    }


    fun addList(mDataset: MutableList<Team>) {
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
                    .inflate(R.layout.row_item_team, parent, false)

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

        fun bind(item: Team) = with(itemView) {
            item.strTeamBadge?.let { Utils.loadImage(ivTeam.context,ivTeam, it) }
            tvTeamTitle.text = item.strTeam

            setOnClickListener {
                mDataset[adapterPosition]?.let { data ->
                    teamLeaguePresenter?.mView?.goToNextActivity(data)
                    favoriteTeamPresenter?.mView?.goToNextActivity(data)
                }

            }

        }

    }

}