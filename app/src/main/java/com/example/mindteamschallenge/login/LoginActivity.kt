package com.example.mindteamschallenge.login

import android.content.Intent
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
        mBindingData.buttonLogin.setOnClickListener {
            mBindingData.progressBarLogin.visibility = View.VISIBLE
            mLoginPresenter.logIn(mBindingData.inputEmail, mBindingData.inputPassword)
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

    override fun showCredentialsErrorAlert() {
        mBindingData.progressBarLogin.visibility = View.GONE

        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.error_label)
        builder.setMessage(R.string.error_credentials_message)
        builder.setPositiveButton(R.string.accept_label, null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun showHome(email: String) {
        //Analytics test
        val analytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message", "Log in successful")
        analytics.logEvent("LoginScreen", bundle)

        mBindingData.progressBarLogin.visibility = View.GONE

        var homeIntent : Intent  = Intent(this, SuperAdminScreenActivity::class.java)

//        when (mLoginPresenter.getUserRole(email)) {
//            DBConstants.USER_ROLE -> {
//                homeIntent = Intent(this, UserScreenActivity::class.java)
//            }
//            DBConstants.ADMIN_ROLE -> {
//                homeIntent = Intent(this, AdminScreenActivity::class.java)
//            }
//            DBConstants.SUPER_ADMIN_ROLE -> {
//                homeIntent = Intent(this, SuperAdminScreenActivity::class.java)
//            }
//        }

        startActivity(homeIntent)
        finish()
    }
}