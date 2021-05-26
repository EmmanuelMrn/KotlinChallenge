package com.example.mindteamschallenge.detailsoptions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.mindteamschallenge.R

class CreateUserFragment : DialogFragment() {
    private lateinit var mDatabaseHelper: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.fragment_create_user, container, false)
        mDatabaseHelper = DatabaseHelper()

        var nameEditText : EditText = rootView.findViewById(R.id.create_user_edittext_name) as EditText
        var emailEditText : EditText = rootView.findViewById(R.id.create_user_edittext_email) as EditText
        var passwordEditText : EditText = rootView.findViewById(R.id.create_user_edittext_password) as EditText
        var englishLevelEditText : EditText = rootView.findViewById(R.id.create_user_edittext_english_level) as EditText
        var techKnowledgeEditText : EditText = rootView.findViewById(R.id.create_user_edittext_knowledge_tech) as EditText
        var cvLinkEditText : EditText = rootView.findViewById(R.id.create_user_edittext_curriculum) as EditText
        var accountNameEditText : EditText = rootView.findViewById(R.id.create_user_edittext_account_team) as EditText
        var startDateEditText : EditText = rootView.findViewById(R.id.create_user_edittext_start_date) as EditText
        var endingDateEditText : EditText = rootView.findViewById(R.id.create_user_edittext_ending_date) as EditText

        val createUserButton : Button = rootView.findViewById(R.id.create_user_accept_button) as Button
        var createdUserSuccessful = false

        createUserButton.setOnClickListener {
            createdUserSuccessful = mDatabaseHelper.createUser(nameEditText.text.toString(),
                emailEditText.text.toString(), passwordEditText.text.toString(),
                englishLevelEditText.text.toString(), techKnowledgeEditText.text.toString(),
                cvLinkEditText.text.toString(), accountNameEditText.text.toString(),
                startDateEditText.text.toString(), endingDateEditText.text.toString())

            if (createdUserSuccessful){
                dismiss()
                Toast.makeText(activity, R.string.user_created_successfully_label, Toast.LENGTH_SHORT).show()
            }
        }

        return rootView
    }

}