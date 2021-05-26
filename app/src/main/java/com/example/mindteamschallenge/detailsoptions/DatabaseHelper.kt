package com.example.mindteamschallenge.detailsoptions

import com.example.mindteamschallenge.utils.DBConstants
import com.google.firebase.firestore.FirebaseFirestore

class DatabaseHelper {
    private val mDBAccess = FirebaseFirestore.getInstance()

    fun createUser(
        name: String, email: String, password: String, englishLevel: String, techKnowledge: String,
        cvLink: String, accountName: String, startDate: String, endingDate: String
    ): Boolean {
        var createdUserSuccessful = false
        mDBAccess.collection(DBConstants.USERS_DB_COLLECTION).document(email).set(
            hashMapOf(
                "name" to name,
                "email" to email,
                "password" to password,
                "englishLevel" to englishLevel,
                "techKnowledge" to techKnowledge,
                "cvLink" to cvLink,
                "accountName" to accountName,
                "startDate" to startDate,
                "endingDate" to endingDate,
                "levelAccess" to "User"
            )
        ).addOnSuccessListener {
            createdUserSuccessful = true
        }

        return createdUserSuccessful
    }

    fun getUser(email: String) {
        mDBAccess.collection("users").document(email).get()
            .addOnSuccessListener {

            }
    }
}