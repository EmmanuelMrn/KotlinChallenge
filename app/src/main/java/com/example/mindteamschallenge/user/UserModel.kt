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
                val nameFromDatabase = (it.get(DBConstants.NAME) as String?).toString()
                val emailFromDatabase = (it.get(DBConstants.EMAIL) as String?).toString()
                val englishLevelFromDatabase = (it.get(DBConstants.ENGLISH_LEVEL) as String?).toString()
                val techKnowledgeFromDatabase = (it.get(DBConstants.TECH_KNOWLEDGE) as String?).toString()
                val cvLinkFromDatabase = (it.get(DBConstants.CV_LINK) as String?).toString()
                val levelAccessFromDatabase = (it.get(DBConstants.LEVEL_ACCESS) as String?).toString()
                val accountNameFromDatabase = (it.get(DBConstants.ACCOUNT_NAME) as String?).toString()

                val dataUserFromDatabase = DataUser(
                    nameFromDatabase,
                    emailFromDatabase,
                    englishLevelFromDatabase,
                    techKnowledgeFromDatabase,
                    cvLinkFromDatabase,
                    levelAccessFromDatabase,
                    accountNameFromDatabase
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
        mDBAccess.collection(DBConstants.USERS_DB_COLLECTION).document(dataForUpdate.email).update(
            hashMapOf(
                DBConstants.NAME to dataForUpdate.name,
                DBConstants.EMAIL to dataForUpdate.email,
                DBConstants.ENGLISH_LEVEL to dataForUpdate.englishLevel,
                DBConstants.TECH_KNOWLEDGE to dataForUpdate.techKnowledge,
                DBConstants.CV_LINK to dataForUpdate.cvLink,
                DBConstants.LEVEL_ACCESS to dataForUpdate.levelAccess,
                DBConstants.ACCOUNT_NAME to dataForUpdate.accountName
            ) as Map<String, Any>
        ).addOnSuccessListener {
            successListener("success")
        }.addOnFailureListener {
            errorListener("error")
        }
    }
}