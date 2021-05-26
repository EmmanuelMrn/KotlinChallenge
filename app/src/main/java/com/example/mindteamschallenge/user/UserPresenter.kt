package com.example.mindteamschallenge.user

import com.google.firebase.auth.FirebaseAuth


class UserPresenter(
    private val userScreenActivity: UserContract.View,
    private val userModel: UserContract.Model
) : UserContract.Presenter {

    override fun getDetailInfo() {
        TODO("Not yet implemented")
    }

    override fun logout() {
        FirebaseAuth.getInstance().signOut()
        userScreenActivity.goToLoginScreen()
    }
}