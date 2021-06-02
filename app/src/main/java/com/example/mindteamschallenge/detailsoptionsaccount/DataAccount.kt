package com.example.mindteamschallenge.detailsoptionsaccount

data class DataAccount (
    val accountName: String,
    val clientName: String,
    val responsibleOperation: String,
    val team: List<String>
)