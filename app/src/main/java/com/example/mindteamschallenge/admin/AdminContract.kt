package com.example.mindteamschallenge.admin

import android.content.Context
import android.widget.EditText

interface AdminContract {
    interface View {
        fun showSuccessAlert(title : String, message : String)
        fun showErrorAlert(title : String, message : String)
        fun goToLoginScreen()
    }

    interface Presenter {
        fun createUser(context: Context, inputEmail: EditText, inputPassword: EditText)
        fun deleteUser()
        fun updateUser()
        fun consultUser()
        fun moveUserAnotherAccount()
        fun consultLogMovements()


        fun createAccount()
        fun deleteAccount()
        fun updateAccount()
        fun consultAccount()

        fun logout()
    }

    interface Model {
    }
}