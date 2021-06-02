package com.example.mindteamschallenge.utils

import android.util.Log
import com.example.mindteamschallenge.detailsoptionsaccount.DataAccount
import com.example.mindteamschallenge.detailsoptionsuser.DataUserRegister
import com.example.mindteamschallenge.user.DataUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class DatabaseHelper {
    private val mDBAccess = FirebaseFirestore.getInstance()

    fun createUser(dataUserRegister: DataUserRegister) {
        mDBAccess.collection(DBConstants.USERS_DB_COLLECTION).document(dataUserRegister.email).set(
            hashMapOf(
                DBConstants.NAME to dataUserRegister.name,
                DBConstants.EMAIL to dataUserRegister.email,
                DBConstants.LEVEL_ACCESS to dataUserRegister.levelAccess
            )
        ).addOnSuccessListener {
            createUserWithEmailAndPassword(dataUserRegister)
        }
    }

    private fun createUserWithEmailAndPassword(dataUserRegister: DataUserRegister) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
            dataUserRegister.email,
            dataUserRegister.password
        )
    }

    fun getUser(
        email: String,
        successListener: (DataUser) -> Unit,
    ) {
        var dataUser: DataUser
        mDBAccess.collection(DBConstants.USERS_DB_COLLECTION).document(email).get()
            .addOnSuccessListener { result ->
                dataUser = DataUser(
                    result.data?.get(DBConstants.NAME).toString(),
                    result.data?.get(DBConstants.EMAIL).toString(),
                    result.data?.get(DBConstants.ENGLISH_LEVEL).toString(),
                    result.data?.get(DBConstants.TECH_KNOWLEDGE).toString(),
                    result.data?.get(DBConstants.CV_LINK).toString(),
                    result.data?.get(DBConstants.LEVEL_ACCESS).toString(),
                    result.data?.get(DBConstants.ACCOUNT_NAME).toString(),
                )
                successListener(dataUser)
            }
    }

    fun getUsers(successListener: (MutableList<String>, MutableList<String>) -> Unit) {
        val usersList = mutableListOf<String>()
        val usersListLevelAccess = mutableListOf<String>()
        var iterator = 0

        mDBAccess.collection(DBConstants.USERS_DB_COLLECTION)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    if (document.data[DBConstants.LEVEL_ACCESS].toString() != DBConstants.SUPER_ADMIN_ROLE) {
                        usersList.add(iterator, document.data[DBConstants.EMAIL].toString())
                        usersListLevelAccess.add(
                            iterator,
                            document.data[DBConstants.LEVEL_ACCESS].toString()
                        )
                        iterator++
                        Log.d("tag", "$iterator => ${document.id} => ${document.data}")
                    }
                }

                successListener(usersList, usersListLevelAccess)
            }
    }

    fun deleteUser(email: String, successListener: (String) -> Unit) {
        mDBAccess.collection(DBConstants.USERS_DB_COLLECTION).document(email).delete()
            .addOnSuccessListener {
                successListener("successful")
            }
    }

    fun updateUser(email: String) {

    }

    fun createNewAccount(dataAccount: DataAccount) {
        mDBAccess.collection(DBConstants.ACCOUNTS_DB_COLLECTION)
            .document(dataAccount.accountName).set(
                hashMapOf(
                    DBConstants.ACCOUNT_NAME to dataAccount.accountName,
                    DBConstants.ACCOUNT_CLIENT_NAME to dataAccount.clientName,
                    DBConstants.ACCOUNT_OP_RESPONSIBLE to dataAccount.responsibleOperation,
                    DBConstants.ACCOUNT_TEAM to dataAccount.team
                )
            )
    }

    fun getAccount(accountName: String, successListener: (DataAccount) -> Unit) {
        var dataAccount: DataAccount
        mDBAccess.collection(DBConstants.ACCOUNTS_DB_COLLECTION).document(accountName).get()
            .addOnSuccessListener { result ->
                val memberList = listOf(result.data?.get(DBConstants.ACCOUNT_TEAM).toString())
                dataAccount = DataAccount(
                    result.data?.get(DBConstants.ACCOUNT_NAME).toString(),
                    result.data?.get(DBConstants.ACCOUNT_CLIENT_NAME).toString(),
                    result.data?.get(DBConstants.ACCOUNT_OP_RESPONSIBLE).toString(),
                    memberList
                )
                successListener(dataAccount)
            }
    }

    fun getAccounts(accountName: String, successListener: (MutableList<String>) -> Unit) {
        val accountsList = mutableListOf<String>()
        var iterator = 0

        mDBAccess.collection(DBConstants.ACCOUNTS_DB_COLLECTION)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    if (document.data[DBConstants.ACCOUNT_NAME].toString() != accountName) {
                        accountsList.add(
                            iterator,
                            document.data[DBConstants.ACCOUNT_NAME].toString()
                        )
                        iterator++
                        Log.d("tag", "$iterator => ${document.id} => ${document.data}")
                    }
                }

                successListener(accountsList)
            }
    }

    fun deleteAccount(accountName: String, successListener: (String) -> Unit) {
        mDBAccess.collection(DBConstants.ACCOUNTS_DB_COLLECTION).document(accountName).delete()
            .addOnSuccessListener {
                successListener("successful")
            }
    }

    fun updateMemberTeam(userID: String, accountName: String, successListener: (String) -> Unit) {
        var userPreviousAccount: String
        mDBAccess.collection(DBConstants.USERS_DB_COLLECTION).document(userID).get()
            .addOnSuccessListener { result ->
                userPreviousAccount = result.data?.get(DBConstants.ACCOUNT_NAME).toString()
                mDBAccess.collection(DBConstants.ACCOUNTS_DB_COLLECTION)
                    .document(userPreviousAccount)
                    .update(DBConstants.ACCOUNT_TEAM, FieldValue.arrayRemove(userID))
                    .addOnSuccessListener {
                        Log.d("tag", "Previous account: $userPreviousAccount")
                        mDBAccess.collection(DBConstants.ACCOUNTS_DB_COLLECTION)
                            .document(accountName)
                            .update(DBConstants.ACCOUNT_TEAM, FieldValue.arrayUnion(userID)).
                                addOnSuccessListener {
                                    Log.d("tag", "New account: $accountName")
                                    mDBAccess.collection(DBConstants.USERS_DB_COLLECTION).document(userID)
                                        .update(DBConstants.ACCOUNT_NAME, accountName).addOnSuccessListener {
                                            successListener("successful")
                                        }
                                }
                    }
            }
    }
}