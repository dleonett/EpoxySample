package com.leonett.photofeed.data.model

import java.io.Serializable

data class Message(
    val id: String,
    val body: String,
    val timestamp: String,
    val sender: User,
    val receiver: User
) : Serializable