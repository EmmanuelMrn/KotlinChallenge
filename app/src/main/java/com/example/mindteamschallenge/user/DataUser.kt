package com.example.mindteamschallenge.user

data class DataUser(
    val name: String,
    val email: String,
    val englishLevel: String,
    val techKnowledge: String,
    val cvLink: String,
    val levelAccess: String = "User",
    val accountName: String
)