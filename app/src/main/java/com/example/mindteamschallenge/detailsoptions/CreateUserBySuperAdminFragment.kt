package com.example.mindteamschallenge.detailsoptions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.mindteamschallenge.R

class CreateUserBySuperAdminFragment : DialogFragment() {
    private lateinit var mDatabaseHelper: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.fragment_create_user_by_super_admin, container, false)
        mDatabaseHelper = DatabaseHelper()

        val createUser : Button = rootView.findViewById(R.id.create_user_accept_button_by_super_admin) as Button
        createUser.setOnClickListener {
//            mDatabaseHelper.createUser()
        }

        return rootView
    }

}