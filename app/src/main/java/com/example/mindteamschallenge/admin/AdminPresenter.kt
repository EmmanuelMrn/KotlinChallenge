package com.example.mindteamschallenge.admin

import android.content.Context
import android.widget.EditText
import com.example.mindteamschallenge.R
import com.google.firebase.auth.FirebaseAuth


class AdminPresenter(
    private val adminActivity: AdminContract.View,
    private val adminModel: AdminContract.Model
) : AdminContract.Presenter {

    override fun createUser(context: Context, inputEmail: EditText, inputPassword: EditText) {

        if (inputEmail.text.isNotEmpty() && inputPassword.text.isNotEmpty()) {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                inputEmail.text.toString(),
                inputPassword.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    adminActivity.showSuccessAlert(context.getString(R.string.success_label), context.getString(
                        R.string.user_created_successfully_label))
                } else {
                    adminActivity.showErrorAlert(context.getString(R.string.error_label), context.getString(
                        R.string.error_occurred_message))
                }
            }
        }

    }

    override fun deleteUser() {
        TODO("Not yet implemented")
    }

    override fun updateUser() {
        TODO("Not yet implemented")
    }

    override fun consultUser() {
        TODO("Not yet implemented")
    }

    override fun moveUserAnotherAccount() {
        TODO("Not yet implemented")
    }

    override fun consultLogMovements() {
        TODO("Not yet implemented")
    }

    override fun createAccount() {
        TODO("Not yet implemented")
    }

    override fun deleteAccount() {
        TODO("Not yet implemented")
    }

    override fun updateAccount() {
        TODO("Not yet implemented")
    }

    override fun consultAccount() {
        TODO("Not yet implemented")
    }

    override fun logout() {
        FirebaseAuth.getInstance().signOut()
        adminActivity.goToLoginScreen()
    }
}