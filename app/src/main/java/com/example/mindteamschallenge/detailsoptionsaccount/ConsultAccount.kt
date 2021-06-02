package com.example.mindteamschallenge.detailsoptionsaccount

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mindteamschallenge.R
import com.example.mindteamschallenge.databinding.ActivityConsultAccountBinding
import com.example.mindteamschallenge.utils.DBConstants
import com.example.mindteamschallenge.utils.DatabaseHelper

class ConsultAccount : AppCompatActivity() {
    private lateinit var mBindingData: ActivityConsultAccountBinding
    private lateinit var mDatabaseHelper: DatabaseHelper

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var teamAdapter: RecyclerView.Adapter<TeamAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBindingData = ActivityConsultAccountBinding.inflate(layoutInflater)
        setContentView(mBindingData.root)

        mDatabaseHelper = DatabaseHelper()

        val bundle = intent.extras
        val accountName = bundle?.getString(DBConstants.ACCOUNT_NAME)

        if (accountName != null) {
            mDatabaseHelper.getAccount(accountName) { accountInfo ->
                Log.d("tag", "Consulted Account: " + accountInfo.accountName)
                showDetailInfo(accountInfo)
            }
        }

        mBindingData.consultAccountCancelChangesButton.setOnClickListener {
            onBackPressed()
        }

        mBindingData.consultAccountDeleteAccountButton.setOnClickListener {
            if (accountName != null) {
                showConfirmDeleteAlert(accountName)
            }
        }
    }

    private fun showDetailInfo(dataAccount: DataAccount){
        mBindingData.consultAccountNameTextview.text = dataAccount.accountName
        mBindingData.consultClientNameTextview.text = dataAccount.clientName
        mBindingData.consultOpResponsibleTextview.text = dataAccount.responsibleOperation

        layoutManager = LinearLayoutManager(this)
        mBindingData.consultAccountRecyclerview.setHasFixedSize(true)
        mBindingData.consultAccountRecyclerview.layoutManager = layoutManager
        teamAdapter = TeamAdapter(this, dataAccount.team.toMutableList())
        mBindingData.consultAccountRecyclerview.adapter = teamAdapter
    }

    private fun showCompleteDeleteAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.delete_account_label)
        builder.setMessage(R.string.account_deleted_successfully)
        builder.setPositiveButton(R.string.accept_label) { _, _ ->
            onBackPressed()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showConfirmDeleteAlert(accountName: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.delete_account_label)
        builder.setMessage(R.string.confirm_delete_account)
        builder.setPositiveButton(R.string.yes_label) { _, _ ->
            mDatabaseHelper.deleteAccount(accountName){
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