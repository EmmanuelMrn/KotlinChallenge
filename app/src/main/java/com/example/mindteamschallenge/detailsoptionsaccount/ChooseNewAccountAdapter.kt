package com.example.mindteamschallenge.detailsoptionsaccount

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mindteamschallenge.R
import com.example.mindteamschallenge.utils.DatabaseHelper

class ChooseNewAccountAdapter(activity: Activity, userConsulted: String, listOfAccounts: MutableList<String>) :
    RecyclerView.Adapter<ChooseNewAccountAdapter.ViewHolder>() {
    private val mDatabaseHelper: DatabaseHelper = DatabaseHelper()
    private var accountsList = listOfAccounts
    private var userId = userConsulted
    private val context = activity

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemTitle: TextView

        init {
            itemTitle = itemView.findViewById(R.id.team_member_item)

            itemTitle.setOnClickListener {
                mDatabaseHelper.updateMemberTeam(userId, itemTitle.text.toString()) {
                    context.onBackPressed()
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChooseNewAccountAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_new_account_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChooseNewAccountAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text = accountsList[position]
    }

    override fun getItemCount(): Int {
        return accountsList.size
    }
}