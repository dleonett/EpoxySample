package com.leonett.epoxysample.data.model

import com.google.gson.annotations.SerializedName

data class Story(
    @SerializedName("id")
    var id: Int,
    @SerializedName("avatarUrl")
    var imgUrl: String,
    @SerializedName("username")
    var username: String
)