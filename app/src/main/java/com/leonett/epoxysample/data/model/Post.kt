package com.leonett.epoxysample.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Post(
    @SerializedName("id")
    @PrimaryKey
    var id: Int,
    @SerializedName("imgUrl")
    var imgUrl: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("subtitle")
    var subtitle: String?,
    @SerializedName("avatarUrl")
    var avatarUrl: String?,
    @SerializedName("username")
    var username: String?
) : Serializable
