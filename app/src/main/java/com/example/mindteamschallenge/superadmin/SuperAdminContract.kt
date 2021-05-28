package com.example.mindteamschallenge.superadmin

import android.content.Context
import android.widget.EditText

interface SuperAdminContract {
    interface View {
        fun showSuccessAlert(title : String, message : String)
        fun showErrorAlert(title : String, message : String)
        fun goToLoginScreen()
    }

    interface Presenter {
        fun logout()
    }

    interface Model {
    }
}