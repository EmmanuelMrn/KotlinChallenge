package com.example.mindteamschallenge.superadmin

import android.content.Context
import android.widget.EditText
import com.example.mindteamschallenge.R
import com.google.firebase.auth.FirebaseAuth

class SuperAdminPresenter(
    private val superAdminActivity: SuperAdminContract.View,
    private val superAdminModel: SuperAdminContract.Model
) : SuperAdminContract.Presenter {

    override fun createUser(context: Context, inputEmail: EditText, inputPassword: EditText) {

            if (inputEmail.text.isNotEmpty() && inputPassword.text.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    inputEmail.text.toString(),
                    inputPassword.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        superAdminActivity.showSuccessAlert(context.getString(R.string.success_label), context.getString(R.string.user_created_successfully_label))
                    } else {
                        superAdminActivity.showErrorAlert(context.getString(R.string.error_label), context.getString(R.string.error_occurred_message))
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
        superAdminActivity.goToLoginScreen()
    }
}