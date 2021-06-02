package com.example.mindteamschallenge.detailsoptionsuser

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mindteamschallenge.R

class ChooseUserAdapter(activity: Activity, listOfUsers: MutableList<String>, listOfUsersLevelAccess: MutableList<String>) :
    RecyclerView.Adapter<ChooseUserAdapter.ViewHolder>() {
    private var userList = listOfUsers
    private var usersListLevelAccess = listOfUsersLevelAccess
    private val context = activity

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemList: CardView
        var itemTitle: TextView
        var itemLevelAccess: TextView

        init {
            itemList = itemView.findViewById(R.id.item_list)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemLevelAccess = itemView.findViewById(R.id.item_level_access)

            itemList.setOnClickListener {
                val intent: Intent = Intent(context, ConsultUser::class.java).apply {
                    putExtra(context.getString(R.string.email_label), itemTitle.text)
                }
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChooseUserAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChooseUserAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text = userList[position]
        holder.itemLevelAccess.text = usersListLevelAccess[position]
    }

    override fun getItemCount(): Int {
        return userList.size
    }

}