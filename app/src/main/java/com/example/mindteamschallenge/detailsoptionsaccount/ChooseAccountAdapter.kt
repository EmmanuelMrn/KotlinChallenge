package com.example.mindteamschallenge.detailsoptionsaccount

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mindteamschallenge.R
import com.example.mindteamschallenge.utils.DBConstants

class ChooseAccountAdapter(activity: Activity, listOfAccounts: MutableList<String>, listOfAccountsClientName: MutableList<String>) :
    RecyclerView.Adapter<ChooseAccountAdapter.ViewHolder>() {
    private var accountsList = listOfAccounts
    private var clientsListNames = listOfAccountsClientName
    private val context = activity

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemList: CardView
        var itemTitleAccount: TextView
        var itemClientName: TextView

        init {
            itemList = itemView.findViewById(R.id.item_list)
            itemTitleAccount = itemView.findViewById(R.id.item_title)
            itemClientName = itemView.findViewById(R.id.item_level_access)

            itemList.setOnClickListener {
                val intent: Intent = Intent(context, ConsultAccount::class.java).apply {
                    putExtra(DBConstants.ACCOUNT_NAME, itemTitleAccount.text)
                }
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChooseAccountAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChooseAccountAdapter.ViewHolder, position: Int) {
        holder.itemTitleAccount.text = accountsList[position]
        holder.itemClientName.text = clientsListNames[position]
    }

    override fun getItemCount(): Int {
        return accountsList.size
    }
}