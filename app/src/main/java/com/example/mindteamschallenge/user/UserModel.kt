package com.example.mindteamschallenge.user

import com.example.mindteamschallenge.utils.DBConstants
import com.google.firebase.firestore.FirebaseFirestore

class UserModel : UserContract.Model {
    private val mDBAccess = FirebaseFirestore.getInstance()

    override fun getInfoFromDatabase(
        email: String,
        successListener: (DataUser) -> Unit,
        errorListener: (String) -> Unit
    ) {
        mDBAccess.collection(DBConstants.USERS_DB_COLLECTION).document(email).get()
            .addOnSuccessListener {
                val nameFromDatabase = (it.get("name") as String?).toString()
                val emailFromDatabase = (it.get("email") as String?).toString()
                val englishLevelFromDatabase = (it.get("englishLevel") as String?).toString()
                val techKnowledgeFromDatabase = (it.get("techKnowledge") as String?).toString()
                val cvLinkFromDatabase = (it.get("cvLink") as String?).toString()

                val dataUserFromDatabase = DataUser(
                    nameFromDatabase,
                    emailFromDatabase,
                    englishLevelFromDatabase,
                    techKnowledgeFromDatabase,
                    cvLinkFromDatabase
                )

                successListener(dataUserFromDatabase)
            }
            .addOnFailureListener {
                errorListener("error")
            }
    }

    override fun updateInfoInDatabase(
        dataForUpdate: DataUser,
        successListener: (String) -> Unit,
        errorListener: (String) -> Unit
    ) {
        mDBAccess.collection(DBConstants.USERS_DB_COLLECTION).document(dataForUpdate.email).set(
            hashMapOf(
                "name" to dataForUpdate.name,
                "email" to dataForUpdate.email,
                "englishLevel" to dataForUpdate.englishLevel,
                "techKnowledge" to dataForUpdate.techKnowledge,
                "cvLink" to dataForUpdate.cvLink
            )
        ).addOnSuccessListener {
            successListener("success")
        }.addOnFailureListener {
            errorListener("error")
        }
    }
}