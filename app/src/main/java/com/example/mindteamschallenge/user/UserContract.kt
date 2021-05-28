package com.example.mindteamschallenge.user

interface UserContract {
    interface View {
        fun goToLoginScreen()
        fun showDetailInfo(dataUser: DataUser)
        fun showSuccessDataUpdated()
        fun showErrorDataUpdate()
    }

    interface Presenter {
        fun getDetailInfo(email: String)
        fun updateUserInfo(dataForUpdate: DataUser)
        fun logout()
    }

    interface Model {
        fun getInfoFromDatabase(
            email: String,
            successListener: (DataUser) -> Unit,
            errorListener: (String) -> Unit
        )

        fun updateInfoInDatabase(
            dataForUpdate: DataUser,
            successListener: (String) -> Unit,
            errorListener: (String) -> Unit
        )
    }
}