package com.example.mindteamschallenge.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.mindteamschallenge.R
import com.example.mindteamschallenge.admin.AdminScreenActivity
import com.example.mindteamschallenge.databinding.ActivityLoginBinding
import com.example.mindteamschallenge.superadmin.SuperAdminScreenActivity
import com.example.mindteamschallenge.user.UserScreenActivity
import com.example.mindteamschallenge.utils.DBConstants
import com.google.firebase.analytics.FirebaseAnalytics

class LoginActivity : AppCompatActivity(), LoginContract.View {
    private lateinit var mBindingData: ActivityLoginBinding
    private lateinit var mLoginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBindingData = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(mBindingData.root)
        mLoginPresenter = LoginPresenter(this, LoginModel())
        checkSession()


        mBindingData.buttonLogin.setOnClickListener {
            mBindingData.progressBarLogin.visibility = View.VISIBLE
            mLoginPresenter.logIn(mBindingData.inputEmail, mBindingData.inputPassword)
        }
    }

    private fun checkSession() {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences(getString(R.string.shared_preferences_file), Context.MODE_PRIVATE)
        val previousSessionEmail =
            sharedPreferences.getString(getString(R.string.email_label), null)
        val previousSessionRole = sharedPreferences.getString(DBConstants.LEVEL_ACCESS, null)

        if (previousSessionEmail != null && previousSessionRole != null) {
            validateLevelAccess(previousSessionEmail)
        }
    }

    override fun showErrorAlert() {
        mBindingData.progressBarLogin.visibility = View.GONE

        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.error_label)
        builder.setMessage(R.string.error_message)
        builder.setPositiveButton(R.string.accept_label, null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun showConnectionErrorAlert() {
        mBindingData.progressBarLogin.visibility = View.GONE

        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.error_label)
        builder.setMessage(R.string.error_connection_message)
        builder.setPositiveButton(R.string.accept_label, null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun showCredentialsErrorAlert() {
        mBindingData.progressBarLogin.visibility = View.GONE

        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.error_label)
        builder.setMessage(R.string.error_credentials_message)
        builder.setPositiveButton(R.string.accept_label, null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun validateLevelAccess(email: String) {
        mLoginPresenter.getUserRole(email)
    }

    override fun showHome(email: String, userRole: String) {
        //Analytics test
        val analytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message", "Log in successful")
        analytics.logEvent("LoginScreen", bundle)

        var homeIntent: Intent = Intent(this, UserScreenActivity::class.java).apply {
            putExtra(getString(R.string.email_label), email)
            putExtra(getString(R.string.level_access_label), userRole)
        }

        when (userRole) {
            DBConstants.USER_ROLE -> {
                homeIntent = Intent(this, UserScreenActivity::class.java).apply {
                    putExtra(getString(R.string.email_label), email)
                    putExtra(DBConstants.LEVEL_ACCESS, userRole)
                }
            }
            DBConstants.ADMIN_ROLE -> {
                homeIntent = Intent(this, AdminScreenActivity::class.java).apply {
                    putExtra(getString(R.string.email_label), email)
                    putExtra(DBConstants.LEVEL_ACCESS, userRole)
                }
            }
            DBConstants.SUPER_ADMIN_ROLE -> {
                homeIntent = Intent(this, SuperAdminScreenActivity::class.java).apply {
                    putExtra(getString(R.string.email_label), email)
                    putExtra(DBConstants.LEVEL_ACCESS, userRole)
                }
            }
        }

        mBindingData.progressBarLogin.visibility = View.GONE

        startActivity(homeIntent)
        finish()
    }


}