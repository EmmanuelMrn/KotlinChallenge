package com.example.mindteamschallenge.detailsoptionsuser

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.mindteamschallenge.R
import com.example.mindteamschallenge.utils.DBConstants
import com.example.mindteamschallenge.utils.DatabaseHelper

class CreateUserBySuperAdminFragment : DialogFragment() {
    private lateinit var mDatabaseHelper: DatabaseHelper
    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var levelAccessUser: RadioButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView =
            inflater.inflate(R.layout.fragment_create_user_by_super_admin, container, false)
        mDatabaseHelper = DatabaseHelper()

        nameEditText =
            rootView.findViewById(R.id.create_user_edittext_name_by_super_admin) as EditText
        emailEditText =
            rootView.findViewById(R.id.create_user_edittext_email_by_super_admin) as EditText
        passwordEditText =
            rootView.findViewById(R.id.create_user_edittext_password_by_super_admin) as EditText
        levelAccessUser =
            rootView.findViewById(R.id.create_user_is_user_radiotbutton_by_super_admin) as RadioButton


        val createUserButton: Button =
            rootView.findViewById(R.id.create_user_accept_button_by_super_admin) as Button
        val cancelButton: Button =
            rootView.findViewById(R.id.create_user_cancel_button_by_super_admin)

        createUserButton.setOnClickListener {
            createNewUser()
            dismiss()
            hideKeyboard()
        }

        cancelButton.setOnClickListener {
            dismiss()
            hideKeyboard()
        }

        return rootView
    }

    private fun createNewUser() {
        if (nameEditText.text.isNotEmpty() && emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()) {
            var levelAccess = DBConstants.ADMIN_ROLE
            if (levelAccessUser.isChecked) {
                levelAccess = DBConstants.USER_ROLE
            }
            val dataUserRegister = DataUserRegister(
                nameEditText.text.toString(),
                emailEditText.text.toString(),
                passwordEditText.text.toString(),
                levelAccess
            )
            mDatabaseHelper.createUser(dataUserRegister)
            Toast.makeText(activity, R.string.user_created_successfully_label, Toast.LENGTH_SHORT).show()
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