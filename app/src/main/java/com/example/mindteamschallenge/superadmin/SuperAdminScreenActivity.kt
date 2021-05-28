package com.example.mindteamschallenge.superadmin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.mindteamschallenge.login.LoginActivity
import com.example.mindteamschallenge.R
import com.example.mindteamschallenge.databinding.ActivitySuperAdminScreenBinding
import com.example.mindteamschallenge.detailsoptions.CreateAccountFragment
import com.example.mindteamschallenge.detailsoptions.CreateUserBySuperAdminFragment
import com.example.mindteamschallenge.utils.DBConstants

class SuperAdminScreenActivity : AppCompatActivity(), SuperAdminContract.View {
    private lateinit var mBindingData: ActivitySuperAdminScreenBinding
    private lateinit var mSuperAdminPresenter: SuperAdminPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBindingData = ActivitySuperAdminScreenBinding.inflate(layoutInflater)
        setContentView(mBindingData.root)
        mSuperAdminPresenter = SuperAdminPresenter(this, SuperAdminModel())

        val bundle = intent.extras
        val email = bundle?.getString(getString(R.string.email_label))

        mBindingData.createUserSuperAdminButton.setOnClickListener {
            showCreateUserFragment()
        }

        mBindingData.consultUserSuperAdminButton.setOnClickListener {

        }

        mBindingData.createAccountSuperAdminButton.setOnClickListener {
            showCreateAccountFragment()
        }

        mBindingData.consultAccountSuperAdminButton.setOnClickListener {

        }

        mBindingData.logoutSuperAdminButton.setOnClickListener {
            deleteSession()
            mSuperAdminPresenter.logout()
        }

        if (email != null) {
            saveSession(email)
        }

        Toast.makeText(this, getString(R.string.welcome_super_admin_message), Toast.LENGTH_SHORT).show()
    }

    private fun showCreateUserFragment() {
        val dialog = CreateUserBySuperAdminFragment()

        dialog.show(supportFragmentManager, "create_user_dialog")
    }

    private fun showCreateAccountFragment() {
        var dialog = CreateAccountFragment()

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

    private fun saveSession(email: String) {
        val sharedPreferences: SharedPreferences.Editor = getSharedPreferences(
            getString(R.string.shared_preferences_file),
            Context.MODE_PRIVATE
        ).edit()
        sharedPreferences.putString(getString(R.string.email_label), email)
        sharedPreferences.putString(DBConstants.LEVEL_ACCESS, DBConstants.SUPER_ADMIN_ROLE)
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