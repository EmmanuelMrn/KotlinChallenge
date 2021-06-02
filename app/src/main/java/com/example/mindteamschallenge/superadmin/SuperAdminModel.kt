package com.example.mindteamschallenge.superadmin

import android.util.Log
import com.example.mindteamschallenge.utils.DBConstants
import com.google.firebase.firestore.FirebaseFirestore

class SuperAdminModel : SuperAdminContract.Model {
    private val mDBAccess = FirebaseFirestore.getInstance()

    override fun getUsersFromDB(successListener: (MutableList<String>, MutableList<String>) -> Unit) {
        val usersList = mutableListOf<String>()
        val usersListLevelAccess = mutableListOf<String>()
        var iterator = 0

        mDBAccess.collection(DBConstants.USERS_DB_COLLECTION)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    if (document.data[DBConstants.LEVEL_ACCESS].toString() != DBConstants.SUPER_ADMIN_ROLE) {
                        usersList.add(iterator, document.data[DBConstants.EMAIL].toString())
                        usersListLevelAccess.add(iterator, document.data[DBConstants.LEVEL_ACCESS].toString())
                        iterator++
                        Log.d("tag", "$iterator => ${document.id} => ${document.data}")
                    }
                }

                successListener(usersList, usersListLevelAccess)
            }
    }

    override fun getAccountsFromDB(successListener: (MutableList<String>, MutableList<String>) -> Unit) {
        val accountsList = mutableListOf<String>()
        val clientsNamesList = mutableListOf<String>()
        var iterator = 0

        mDBAccess.collection(DBConstants.ACCOUNTS_DB_COLLECTION)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    accountsList.add(iterator, document.data[DBConstants.ACCOUNT_NAME].toString())
                    clientsNamesList.add(iterator, document.data[DBConstants.ACCOUNT_CLIENT_NAME].toString())
                    iterator++
                    Log.d("tag", "$iterator => ${document.id} => ${document.data}")

                }

                successListener(accountsList, clientsNamesList)
            }
    }
}