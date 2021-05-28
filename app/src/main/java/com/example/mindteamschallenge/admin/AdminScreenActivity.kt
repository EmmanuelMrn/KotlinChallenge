package com.example.mindteamschallenge.admin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.mindteamschallenge.login.LoginActivity
import com.example.mindteamschallenge.R
import com.example.mindteamschallenge.databinding.ActivityAdminScreenBinding
import com.example.mindteamschallenge.detailsoptions.CreateAccountFragment
import com.example.mindteamschallenge.detailsoptions.CreateUserFragment
import com.example.mindteamschallenge.utils.DBConstants

class AdminScreenActivity : AppCompatActivity(), AdminContract.View {
    private lateinit var mBindingData: ActivityAdminScreenBinding
    private lateinit var mAdminPresenter: AdminPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBindingData = ActivityAdminScreenBinding.inflate(layoutInflater)
        setContentView(mBindingData.root)
        mAdminPresenter = AdminPresenter(this, AdminModel())


        mBindingData.createUserAdminButton.setOnClickListener {
            showCreateUserFragment()
        }

        mBindingData.createAccountAdminButton.setOnClickListener {
            showCreateAccountFragment()
        }

        mBindingData.logoutAdminButton.setOnClickListener {
            deleteSession()
            mAdminPresenter.logout()
        }

        saveSession()

        Toast.makeText(this, getString(R.string.welcome_admin_message), Toast.LENGTH_SHORT).show()
    }

    private fun showCreateUserFragment() {
        val dialog = CreateUserFragment()

        dialog.show(supportFragmentManager, "create_user_dialog")
    }

    private fun showCreateAccountFragment() {
        val dialog = CreateAccountFragment()

        dialog.show(supportFragmentManager, "create_account_dialog")
    }

    override fun showSuccessAlert(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(R.string.accept_label, null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun showErrorAlert(title: String, message: String) {
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

    private fun saveSession() {
        val bundle = intent.extras
        val email = bundle?.getString(getString(R.string.email_label))
        val sharedPreferences: SharedPreferences.Editor = getSharedPreferences(
            getString(R.string.shared_preferences_file),
            Context.MODE_PRIVATE
        ).edit()
        sharedPreferences.putString(getString(R.string.email_label), email)
        sharedPreferences.putString(DBConstants.LEVEL_ACCESS, DBConstants.ADMIN_ROLE)
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