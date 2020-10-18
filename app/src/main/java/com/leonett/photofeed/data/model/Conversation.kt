package com.leonett.photofeed.data.model

import java.io.Serializable

data class Conversation(val id: String, val user: User, val lastMessage: Message? = null) :
    Serializable