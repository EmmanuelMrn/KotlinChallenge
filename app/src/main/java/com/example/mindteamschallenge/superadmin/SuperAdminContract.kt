package com.example.mindteamschallenge.superadmin

interface SuperAdminContract {
    interface View {
        fun showSuccessAlert(title : String, message : String)
        fun showErrorAlert(title : String, message : String)
        fun showChooseUserFragment(usersList: MutableList<String>, usersListLevelAccess: MutableList<String>)
        fun showChooseAccountFragment(accountsList: MutableList<String>, clientsNamesList: MutableList<String>)
        fun goToLoginScreen()
    }

    interface Presenter {
        fun getUsersList()
        fun getAccountsList()
        fun logout()
    }

    interface Model {
        fun getUsersFromDB(successListener: (MutableList<String>, MutableList<String>) -> Unit)
        fun getAccountsFromDB(successListener: (MutableList<String>, MutableList<String>) -> Unit)
    }
}