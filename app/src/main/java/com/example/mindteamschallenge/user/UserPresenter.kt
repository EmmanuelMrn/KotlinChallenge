package com.example.mindteamschallenge.user

import com.google.firebase.auth.FirebaseAuth


class UserPresenter(
    private val userScreenActivity: UserContract.View,
    private val userModel: UserContract.Model
) : UserContract.Presenter {

    override fun getDetailInfo(email: String) {
        userModel.getInfoFromDatabase(email, { dataUser ->
            userScreenActivity.showDetailInfo(dataUser)
        }, { error ->

        })
    }

    override fun updateUserInfo(dataForUpdate: DataUser) {
        userModel.updateInfoInDatabase(dataForUpdate, {
            userScreenActivity.showSuccessDataUpdated()
        }, {
            userScreenActivity.showErrorDataUpdate()
        })
    }

    override fun logout() {
        FirebaseAuth.getInstance().signOut()
        userScreenActivity.goToLoginScreen()
    }
}