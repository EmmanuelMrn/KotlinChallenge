package com.example.mindteamschallenge.superadmin

import android.content.Context
import android.widget.EditText
import com.example.mindteamschallenge.R
import com.google.firebase.auth.FirebaseAuth

class SuperAdminPresenter(
    private val superAdminActivity: SuperAdminContract.View,
    private val superAdminModel: SuperAdminContract.Model
) : SuperAdminContract.Presenter {


    override fun logout() {
        FirebaseAuth.getInstance().signOut()
        superAdminActivity.goToLoginScreen()
    }
}