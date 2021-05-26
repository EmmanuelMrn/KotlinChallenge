package com.example.mindteamschallenge.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.mindteamschallenge.login.LoginActivity
import com.example.mindteamschallenge.R
import com.example.mindteamschallenge.databinding.ActivityAdminScreenBinding

class AdminScreenActivity : AppCompatActivity(), AdminContract.View {
    private lateinit var mBindingData: ActivityAdminScreenBinding
    private lateinit var mAdminPresenter: AdminPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBindingData = ActivityAdminScreenBinding.inflate(layoutInflater)
        setContentView(mBindingData.root)

        mAdminPresenter = AdminPresenter(this, AdminModel())

        mBindingData.usersAdminButton.setOnClickListener {

        }

        mBindingData.accountsAdminButton.setOnClickListener {

        }

        mBindingData.logoutAdminButton.setOnClickListener {
            mAdminPresenter.logout()
        }
    }

    override fun showSuccessAlert(title : String, message : String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(R.string.accept_label, null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun showErrorAlert(title : String, message : String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(R.string.accept_label, null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun goToLoginScreen() {
        val intentToLogin = Intent(this, LoginActivity::class.java)
        startActivity(intentToLogin)
        finish()
    }
}