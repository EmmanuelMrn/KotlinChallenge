package com.example.mindteamschallenge.login

import android.widget.EditText

interface LoginContract {
    interface View {
        fun showErrorAlert()
        fun showCredentialsErrorAlert()
        fun showConnectionErrorAlert()
        fun validateLevelAccess(email: String)
        fun showHome(email: String, userRole: String)
    }

    interface Presenter {
        fun logIn(inputEmail: EditText, inputPassword: EditText)
        fun getUserRole(email: String)
    }

    interface Model {
        fun getLevelAccess(
            email: String,
            successListener: (String) -> Unit,
            errorListener: (String) -> Unit
        )
    }
}