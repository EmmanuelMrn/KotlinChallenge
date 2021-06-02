package com.example.mindteamschallenge.superadmin

import com.google.firebase.auth.FirebaseAuth

class SuperAdminPresenter(
    private val superAdminActivity: SuperAdminContract.View,
    private val superAdminModel: SuperAdminContract.Model
) : SuperAdminContract.Presenter {

    override fun getUsersList() {
        superAdminModel.getUsersFromDB { usersList, usersListLevelAccess ->
            superAdminActivity.showChooseUserFragment(usersList, usersListLevelAccess)
        }
    }

    override fun getAccountsList() {
        superAdminModel.getAccountsFromDB() { accountsList, clientsNamesList ->
            superAdminActivity.showChooseAccountFragment(accountsList, clientsNamesList)
        }
    }


    override fun logout() {
        FirebaseAuth.getInstance().signOut()
        superAdminActivity.goToLoginScreen()
    }
}