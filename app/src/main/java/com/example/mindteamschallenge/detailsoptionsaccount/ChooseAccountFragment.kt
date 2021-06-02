package com.example.mindteamschallenge.detailsoptionsaccount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mindteamschallenge.R


class ChooseAccountFragment(listOfAccounts: MutableList<String>, listOfClientsName: MutableList<String>) : DialogFragment() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var accountAdapter: RecyclerView.Adapter<ChooseAccountAdapter.ViewHolder>? = null
    private lateinit var recyclerViewAccounts: RecyclerView
    private val accountsList = listOfAccounts
    private val clientsListNames = listOfClientsName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_choose_account, container, false)
        val cancelButton = rootView.findViewById(R.id.choose_account_cancel_button) as Button
        recyclerViewAccounts =  rootView.findViewById(R.id.choose_account_recycler_view) as RecyclerView

        layoutManager = LinearLayoutManager(activity)
        recyclerViewAccounts.setHasFixedSize(true)
        recyclerViewAccounts.layoutManager = layoutManager
        accountAdapter = ChooseAccountAdapter(activity!!, accountsList, clientsListNames)
        recyclerViewAccounts.adapter = accountAdapter


        cancelButton.setOnClickListener {
            dismiss()
        }
        return rootView
    }
}