package com.example.mindteamschallenge.login

import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth

class LoginPresenter(
    private val loginActivity: LoginContract.View,
    private val loginModel: LoginContract.Model
) : LoginContract.Presenter {

    override fun logIn(inputEmail: EditText, inputPassword: EditText) {

        if (inputEmail.text.isNotEmpty() && inputPassword.text.isNotEmpty()) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                inputEmail.text.toString(),
                inputPassword.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    loginActivity.validateLevelAccess(inputEmail.text.toString())
                } else {
                    loginActivity.showCredentialsErrorAlert()
                }
            }
        } else {
            loginActivity.showErrorAlert()
        }

    }

    override fun getUserRole(email: String) {
        loginModel.getLevelAccess(email, { levelAccess ->
            loginActivity.showHome(email, levelAccess)
        }, {
            loginActivity.showConnectionErrorAlert()
        })
    }
}