package com.example.learncountriesandflagsgame.data.pojos

data class ApiJsonResultItem(
    val capital: List<String>,
    val flags: Flags,
    val name: Name,
    val region: String,
    val independent: Boolean
)