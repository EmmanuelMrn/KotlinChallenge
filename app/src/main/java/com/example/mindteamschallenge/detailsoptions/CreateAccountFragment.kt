package com.example.mindteamschallenge.detailsoptions

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.mindteamschallenge.R
import com.example.mindteamschallenge.utils.DBConstants


class CreateAccountFragment : DialogFragment() {
    private lateinit var mDatabaseHelper: DatabaseHelper
    private lateinit var accountNameEditText: EditText
    private lateinit var accountClientEditText: EditText
    private lateinit var responsibleOperationEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView =  inflater.inflate(R.layout.fragment_create_account, container, false)
        mDatabaseHelper = DatabaseHelper()

        accountNameEditText = rootView.findViewById(R.id.create_account_edittext_name_account) as EditText
        accountClientEditText = rootView.findViewById(R.id.create_account_edittext_name_client) as EditText
        responsibleOperationEditText = rootView.findViewById(R.id.create_account_edittext_responsible_operation) as EditText

        val createAccountButton : Button = rootView.findViewById(R.id.create_account_accept_button) as Button
        val cancelButton: Button = rootView.findViewById(R.id.create_account_cancel_button) as Button

        createAccountButton.setOnClickListener {
            createNewAccount()
            dismiss()
            hideKeyboard()
        }

        cancelButton.setOnClickListener {
            dismiss()
            hideKeyboard()
        }

        return rootView
    }

    private fun createNewAccount() {
        if (accountNameEditText.text.isNotEmpty() && accountClientEditText.text.isNotEmpty() && responsibleOperationEditText.text.isNotEmpty()) {
            val dataAccountRegister = DataAccountRegister(
                accountNameEditText.text.toString(),
                accountClientEditText.text.toString(),
                responsibleOperationEditText.text.toString()
            )
            mDatabaseHelper.createNewAccount(dataAccountRegister)
            Toast.makeText(activity, R.string.account_created_successfully_label, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(activity, R.string.error_message, Toast.LENGTH_LONG).show()
        }
    }

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}