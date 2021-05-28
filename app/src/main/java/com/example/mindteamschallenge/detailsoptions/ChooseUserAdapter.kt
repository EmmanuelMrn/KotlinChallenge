package com.example.mindteamschallenge.detailsoptions

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mindteamschallenge.R
import com.example.mindteamschallenge.user.UserScreenActivity

class ChooseUserAdapter: RecyclerView.Adapter<ChooseUserAdapter.ViewHolder>() {
    private var userList = arrayOf("users1", "users2", "users3")

    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemTitle: TextView

        init {
            itemTitle = itemView.findViewById(R.id.item_title)

            itemTitle.setOnClickListener {
                val position: Int = adapterPosition
//                var intentToUser: Intent = Intent(this, UserScreenActivity::class.java).apply {
//                    putExtra(getString(R.string.email_label), email)
//                }
//                startActivity(intentToUser)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseUserAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChooseUserAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text = userList[position]
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setOnItemClickListener(any: Any) {

    }
}