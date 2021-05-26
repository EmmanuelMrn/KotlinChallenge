package com.example.mindteamschallenge.login

import android.widget.EditText

interface LoginContract {
    interface View {
        fun showErrorAlert()
        fun showCredentialsErrorAlert()
        fun showHome(email: String)
    }

    interface Presenter {
        fun logIn(inputEmail : EditText, inputPassword : EditText)
        fun getUserRole(email : String) : String
    }

    interface Model {
        fun getLevelAccess(email : String) : String
    }
}