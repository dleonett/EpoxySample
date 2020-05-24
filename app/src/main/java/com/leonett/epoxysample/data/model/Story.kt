package com.leonett.epoxysample.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Story(
    @SerializedName("id")
    @PrimaryKey
    var id: Int,
    @SerializedName("avatarUrl")
    var imgUrl: String,
    @SerializedName("username")
    var username: String
) : Serializable