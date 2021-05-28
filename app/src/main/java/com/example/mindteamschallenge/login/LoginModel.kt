package com.example.mindteamschallenge.login

import com.example.mindteamschallenge.utils.DBConstants
import com.google.firebase.firestore.FirebaseFirestore

class LoginModel : LoginContract.Model {
    private val mDBAccess = FirebaseFirestore.getInstance()

    override fun getLevelAccess(
        email: String,
        successListener: (String) -> Unit,
        errorListener: (String) -> Unit
    ) {
        var levelAccess : String

        mDBAccess.collection(DBConstants.USERS_DB_COLLECTION).document(email).get()
            .addOnSuccessListener {
                levelAccess = (it.get(DBConstants.LEVEL_ACCESS) as String?).toString()
                successListener(levelAccess)
            }
            .addOnFailureListener {
                levelAccess = "error"
                errorListener(levelAccess)
            }
    }
}