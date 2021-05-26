package com.example.mindteamschallenge.detailsoptions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.mindteamschallenge.R
import com.example.mindteamschallenge.utils.DBConstants

class UserMenuFragment : DialogFragment() {
    private var mLevelAccess: String? = "User"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mLevelAccess = it.getString(DBConstants.LEVEL_ACCESS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View = inflater.inflate(R.layout.fragment_user_menu, container, false)

        val cancelButton : Button = rootView.findViewById(R.id.user_menu_cancel_button) as Button
        cancelButton.setOnClickListener {
            dismiss()
        }

        val createUserOption : Button = rootView.findViewById(R.id.user_menu_create_user_button) as Button
        createUserOption.setOnClickListener {
            showCreateUserFragment()
        }

        return rootView
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Boolean) =
            UserMenuFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(DBConstants.LEVEL_ACCESS, param1)
                }
            }
    }

    private fun showCreateUserFragment(){
        var dialog = CreateUserFragment()

//        if (levelAccess){
//            dialog = CreateUserBySuperAdminFragment()
//        }

        dismiss()
        fragmentManager?.let { dialog.show(it,"create_user_dialog") }
    }
}