package com.example.mindteamschallenge.detailsoptions

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


class ChooseUserFragment : DialogFragment() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var userAdapter: RecyclerView.Adapter<ChooseUserAdapter.ViewHolder>? = null

    private val mDBAccess = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_choose_user, container, false)
        val cancelButton = rootView.findViewById(R.id.choose_user_cancel_button) as Button
        val recyclerViewUsers =  rootView.findViewById(R.id.choose_user_recycler_view) as RecyclerView

        layoutManager = LinearLayoutManager(activity)
        recyclerViewUsers.layoutManager = layoutManager
        userAdapter = ChooseUserAdapter()

        cancelButton.setOnClickListener {
            dismiss()
        }

//        userAdapter.setOnItemClickListener {
//
//        }

        recyclerViewUsers.adapter = userAdapter
        getUserList()
        return rootView
    }

    private fun showUsers(usersList: MutableList<String>) {

    }

    private fun getUserList() {
        val usersList = mutableListOf<String>()
        var iterator = 0

        mDBAccess.collection(DBConstants.USERS_DB_COLLECTION)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    usersList.add(iterator, document.data.get("email").toString())
                    iterator++
                    Log.d("tag", "${document.id} => ${document.data}")
                }

            }

        showUsers(usersList)
    }
}