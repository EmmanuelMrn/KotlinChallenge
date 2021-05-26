package com.example.mindteamschallenge.user

interface UserContract {
    interface View {
        fun goToLoginScreen()
        fun showDetailInfo()
    }

    interface Presenter {
        fun getDetailInfo()

        fun logout()
    }

    interface Model {
        fun getInfoFromDatabase()
    }
}