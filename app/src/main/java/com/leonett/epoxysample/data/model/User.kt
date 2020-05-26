package com.leonett.epoxysample.data.model

import java.io.Serializable

data class User(
    val username: String,
    val displayName: String,
    val description: String,
    val avatarUrl: String
) : Serializable