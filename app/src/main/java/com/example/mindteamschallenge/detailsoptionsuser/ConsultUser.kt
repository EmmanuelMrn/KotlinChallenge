package com.example.mindteamschallenge.detailsoptionsuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.mindteamschallenge.R
import com.example.mindteamschallenge.databinding.ActivityConsultUserBinding
import com.example.mindteamschallenge.detailsoptionsaccount.ChooseNewAccountFragment
import com.example.mindteamschallenge.user.DataUser
import com.example.mindteamschallenge.utils.DatabaseHelper

class ConsultUser : AppCompatActivity() {
    private lateinit var mBindingData: ActivityConsultUserBinding
    private lateinit var mDatabaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBindingData = ActivityConsultUserBinding.inflate(layoutInflater)
        setContentView(mBindingData.root)

        mDatabaseHelper = DatabaseHelper()

        val bundle = intent.extras
        val email = bundle?.getString(getString(R.string.email_label))

        if (email != null) {
            mDatabaseHelper.getUser(email) { userInfo ->
                Log.d("tag", "Consulted User: " + userInfo.name)
                showDetailInfo(userInfo)
            }
        }

        mBindingData.consultUserModifyAccountButton.setOnClickListener {
            getAccounts()
        }

        mBindingData.consultUserCancelChangesButton.setOnClickListener {
            onBackPressed()
        }

        mBindingData.consultUserDeleteUserButton.setOnClickListener {
            if (email != null) {
                showConfirmDeleteAlert(email)
            }
        }
    }

    private fun getAccounts(){
        mDatabaseHelper.getAccounts(mBindingData.consultUserAccountNameTextview.text.toString()) { accountsList ->
            showChooseNewAccountFragment(accountsList)
        }
    }

    private fun showChooseNewAccountFragment(accountsList: MutableList<String>) {
        val dialog = ChooseNewAccountFragment(mBindingData.consultUserEmailTextview.text.toString(), accountsList)

        dialog.show(supportFragmentManager, "choose_new_account_dialog")
    }

    private fun showDetailInfo(userInfo: DataUser){
        mBindingData.consultUserNameTextview.text = userInfo.name
        mBindingData.consultUserEmailTextview.text = userInfo.email
        mBindingData.consultUserEnglishLevelTextview.text = userInfo.englishLevel
        mBindingData.consultUserTechKnowledgeTextview.text = userInfo.techKnowledge
        mBindingData.consultUserCvLinkTextview.text = userInfo.cvLink
        mBindingData.consultUserLevelAccessTextview.text = userInfo.levelAccess
        mBindingData.consultUserAccountNameTextview.text = userInfo.accountName
    }

    private fun showCompleteDeleteAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.delete_user_label)
        builder.setMessage(R.string.user_deleted_successfully)
        builder.setPositiveButton(R.string.accept_label) { _, _ ->
            onBackPressed()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showConfirmDeleteAlert(email: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.delete_user_label)
        builder.setMessage(R.string.confirm_delete_user)
        builder.setPositiveButton(R.string.yes_label) { _, _ ->
            mDatabaseHelper.deleteUser(email){
                showCompleteDeleteAlert()
            }
        }
        builder.setNegativeButton(R.string.no_label){ dialog, _ ->
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}