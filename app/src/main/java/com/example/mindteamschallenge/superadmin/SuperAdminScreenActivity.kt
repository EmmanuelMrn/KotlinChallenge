package com.example.mindteamschallenge.superadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.mindteamschallenge.login.LoginActivity
import com.example.mindteamschallenge.R
import com.example.mindteamschallenge.databinding.ActivitySuperAdminScreenBinding
import com.example.mindteamschallenge.detailsoptions.UserMenuFragment

class SuperAdminScreenActivity : AppCompatActivity(), SuperAdminContract.View {
    private lateinit var mBindingData: ActivitySuperAdminScreenBinding
    private lateinit var mSuperAdminPresenter: SuperAdminPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBindingData = ActivitySuperAdminScreenBinding.inflate(layoutInflater)
        setContentView(mBindingData.root)

        mSuperAdminPresenter = SuperAdminPresenter(this, SuperAdminModel())

        mBindingData.usersSuperAdminButton.setOnClickListener {
            showUserMenuFragment()
        }

        mBindingData.accountsSuperAdminButton.setOnClickListener {

        }

        mBindingData.logoutSuperAdminButton.setOnClickListener {
            mSuperAdminPresenter.logout()
        }
    }

    private fun showUserMenuFragment(){
        var dialog = UserMenuFragment()

        dialog.show(supportFragmentManager,"user_menu_dialog")
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