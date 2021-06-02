package com.example.mindteamschallenge.admin

import com.google.firebase.auth.FirebaseAuth


class AdminPresenter(
    private val adminActivity: AdminContract.View,
    private val adminModel: AdminContract.Model
) : AdminContract.Presenter {

    override fun getUsersList() {
        adminModel.getUsersFromDB { usersList, usersListLevelAccess ->
            adminActivity.showChooseUserFragment(usersList, usersListLevelAccess)
        }
    }

    override fun getAccountsList() {
        adminModel.getAccountsFromDB() { accountsList, clientsNamesList ->
            adminActivity.showChooseAccountFragment(accountsList, clientsNamesList)
        }
    }


    override fun logout() {
        FirebaseAuth.getInstance().signOut()
        adminActivity.goToLoginScreen()
    }
}