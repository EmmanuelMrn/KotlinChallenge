package com.example.mindteamschallenge.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mindteamschallenge.login.LoginActivity
import com.example.mindteamschallenge.databinding.ActivityUserScreenBinding

class UserScreenActivity : AppCompatActivity(), UserContract.View {
    private lateinit var mBindingData: ActivityUserScreenBinding
    private lateinit var mUserPresenter: UserPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBindingData = ActivityUserScreenBinding.inflate(layoutInflater)
        setContentView(mBindingData.root)
        mUserPresenter = UserPresenter(this, UserModel())

        mBindingData.userLogoutButton.setOnClickListener {
            mUserPresenter.logout()
        }
    }

    override fun goToLoginScreen() {
        val intentToLogin : Intent = Intent(this, LoginActivity::class.java)
        startActivity(intentToLogin)
        finish()
    }

    override fun showDetailInfo() {
        TODO("Not yet implemented")
    }
}