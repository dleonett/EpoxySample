package com.leonett.photofeed.data.model

import java.io.Serializable

data class Message(
    val id: String,
    val body: String,
    val timestamp: String,
    val sender: User,
    val receiver: User
) : Serializable {

    companion object {
        fun mock() = Message("1", "Hello mate!", "2020-10-19 00:15:00", User.mock(), User.mock())
    }

}