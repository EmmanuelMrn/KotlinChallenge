package com.example.mindteamschallenge.detailsoptions

import android.util.Log
import com.example.mindteamschallenge.utils.DBConstants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class DatabaseHelper {
    private val mDBAccess = FirebaseFirestore.getInstance()

    fun createUser(dataUserRegister: DataUserRegister) {
        mDBAccess.collection(DBConstants.USERS_DB_COLLECTION).document(dataUserRegister.email).set(
            hashMapOf(
                "name" to dataUserRegister.name,
                "email" to dataUserRegister.email,
                "levelAccess" to dataUserRegister.levelAccess
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

    fun createNewAccount(dataAccountRegister: DataAccountRegister) {
        mDBAccess.collection(DBConstants.ACCOUNTS_DB_COLLECTION).document(dataAccountRegister.accountName).set(
            hashMapOf(
                "accountName" to dataAccountRegister.accountName,
                "clientName" to dataAccountRegister.clientName,
                "responsibleOperation" to dataAccountRegister.responsibleOperation
            )
        )
    }

    fun getUserList() {
        var usersList = mutableListOf<String>()

        mDBAccess.collection(DBConstants.USERS_DB_COLLECTION)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("tag", "${document.id} => ${document.data}")
                }

            }
    }

    fun getAccountsList() {

    }

//    fun getUser(email: String) {
//        mDBAccess.collection(DBConstants.USERS_DB_COLLECTION).document(email).get()
//            .addOnSuccessListener {
//
//            }
//    }
//
//    fun deleteUser(email: String){
//
//    }
//
//    fun updateUser(email: String){
//
//    }
}