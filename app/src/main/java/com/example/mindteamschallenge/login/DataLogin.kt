package com.example.mindteamschallenge.login

data class DataLogin (
    val name : String,
    val email : String,
    val password : String,
    val englishLevel : String,
    val techKnowledge : String,
    val cvLink : String,
    val accountName : String,
    val startDate : String,
    val endingDate : String,
    var levelAccess : String = "User",
)