package com.example.mindteamschallenge.detailsoptionsaccount

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mindteamschallenge.R

class TeamAdapter(activity: Activity, teamList: MutableList<String>) :
    RecyclerView.Adapter<TeamAdapter.ViewHolder>() {
    private var teamMembersList = teamList
    private val context = activity

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var teamMemberItem: TextView

        init {
            teamMemberItem = itemView.findViewById(R.id.team_member_item)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TeamAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.team_member_item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeamAdapter.ViewHolder, position: Int) {
        holder.teamMemberItem.text = teamMembersList[position]
    }

    override fun getItemCount(): Int {
        return teamMembersList.size
    }
}