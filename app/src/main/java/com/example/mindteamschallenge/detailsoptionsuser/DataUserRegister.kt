package com.example.mindteamschallenge.detailsoptionsuser

data class DataUserRegister(
    val name: String,
    val email: String,
    val password: String,
    var levelAccess : String = "User"
)
