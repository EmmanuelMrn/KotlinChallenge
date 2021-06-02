package com.example.mindteamschallenge.detailsoptionsuser

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mindteamschallenge.R
import com.example.mindteamschallenge.utils.DBConstants
import com.google.firebase.firestore.FirebaseFirestore


class ChooseUserFragment(listOfUsers: MutableList<String>, listOfUsersLevelAccess: MutableList<String>) : DialogFragment() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var userAdapter: RecyclerView.Adapter<ChooseUserAdapter.ViewHolder>? = null
    private lateinit var recyclerViewUsers: RecyclerView
    private val usersList = listOfUsers
    private val usersListLevelAccess = listOfUsersLevelAccess

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_choose_user, container, false)
        val cancelButton = rootView.findViewById(R.id.choose_user_cancel_button) as Button
        recyclerViewUsers =  rootView.findViewById(R.id.choose_user_recycler_view) as RecyclerView

        layoutManager = LinearLayoutManager(activity)
        recyclerViewUsers.setHasFixedSize(true)
        recyclerViewUsers.layoutManager = layoutManager
        userAdapter = ChooseUserAdapter(activity!!, usersList, usersListLevelAccess)
        recyclerViewUsers.adapter = userAdapter

        cancelButton.setOnClickListener {
            dismiss()
        }
        return rootView
    }
}