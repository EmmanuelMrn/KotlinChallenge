package com.example.mindteamschallenge.user

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.mindteamschallenge.R
import com.example.mindteamschallenge.login.LoginActivity
import com.example.mindteamschallenge.databinding.ActivityUserScreenBinding
import com.example.mindteamschallenge.utils.DBConstants

class UserScreenActivity : AppCompatActivity(), UserContract.View {
    private lateinit var mBindingData: ActivityUserScreenBinding
    private lateinit var mUserPresenter: UserPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBindingData = ActivityUserScreenBinding.inflate(layoutInflater)
        setContentView(mBindingData.root)
        mUserPresenter = UserPresenter(this, UserModel())

        val bundle = intent.extras
        val email = bundle?.getString(getString(R.string.email_label))

        if (email != null) {
            mUserPresenter.getDetailInfo(email)
        }

        mBindingData.userSaveChangesButton.setOnClickListener {
            val dataForUpdate = DataUser(
                mBindingData.userNameEdittext.text.toString(),
                mBindingData.userEmailTextview.text.toString(),
                mBindingData.userEnglishLevelEdittext.text.toString(),
                mBindingData.userKnowledgeEdittext.text.toString(),
                mBindingData.userLinkCvEdittext.text.toString(),
                DBConstants.USER_ROLE,
                mBindingData.userAccountNameTextview.text.toString()
            )
            mUserPresenter.updateUserInfo(dataForUpdate)
        }

        mBindingData.userLogoutButton.setOnClickListener {
            deleteSession()

            mUserPresenter.logout()
        }

        if (email != null) {
            saveSession(email)
        }

        Toast.makeText(this, getString(R.string.welcome_user_message), Toast.LENGTH_SHORT).show()
    }

    override fun goToLoginScreen() {
        val intentToLogin = Intent(this, LoginActivity::class.java)
        startActivity(intentToLogin)
        finish()
    }

    override fun showDetailInfo(dataUser: DataUser) {
        mBindingData.userNameEdittext.setText(dataUser.name)
        mBindingData.userEmailTextview.text = dataUser.email
        mBindingData.userEnglishLevelEdittext.setText(dataUser.englishLevel)
        mBindingData.userKnowledgeEdittext.setText(dataUser.techKnowledge)
        mBindingData.userLinkCvEdittext.setText(dataUser.cvLink)
        mBindingData.userAccountNameTextview.text = dataUser.accountName
    }

    override fun showSuccessDataUpdated() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.success_label)
        builder.setMessage(R.string.success_updating_data)
        builder.setPositiveButton(R.string.accept_label, null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun showErrorDataUpdate() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.error_label)
        builder.setMessage(R.string.error_updating_data)
        builder.setPositiveButton(R.string.accept_label, null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun saveSession(email: String) {
        val sharedPreferences: SharedPreferences.Editor = getSharedPreferences(
            getString(R.string.shared_preferences_file),
            Context.MODE_PRIVATE
        ).edit()
        sharedPreferences.putString(getString(R.string.email_label), email)
        sharedPreferences.putString(DBConstants.LEVEL_ACCESS, DBConstants.USER_ROLE)
        sharedPreferences.apply()
    }

    private fun deleteSession() {
        val sharedPreferences: SharedPreferences.Editor = getSharedPreferences(
            getString(R.string.shared_preferences_file),
            Context.MODE_PRIVATE
        ).edit()
        sharedPreferences.clear()
        sharedPreferences.apply()
    }
}