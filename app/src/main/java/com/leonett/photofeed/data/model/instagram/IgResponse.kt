package com.leonett.photofeed.data.model.instagram

import com.google.gson.annotations.SerializedName

data class IgResponse(@SerializedName("graphql") val graphQl: IgGraphQl)

data class IgGraphQl(@SerializedName("user") val user: IgUser)

data class IgUser(
    @SerializedName("biography") val biography: String,
    @SerializedName("external_url") val externalUrl: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("id") val id: String,
    @SerializedName("is_private") val isPrivate: Boolean,
    @SerializedName("is_verified") val isVerified: Boolean,
    @SerializedName("profile_pic_url") val profilePicUrl: String,
    @SerializedName("profile_pic_url_hd") val profilePicUrlHd: String,
    @SerializedName("username") val username: String,
    @SerializedName("edge_followed_by") val edgeFollowedBy: IgEdge,
    @SerializedName("edge_follow") val edgeFollow: IgEdge,
    @SerializedName("edge_owner_to_timeline_media") val edgeOwnerToTimelineMedia: IgEdge
)

data class IgEdge(val count: Int, val node: IgNode?, val edges: List<IgEdge>?)

data class IgNode(
    @SerializedName("id") val id: String,
    @SerializedName("text") val text: String,
    @SerializedName("location") val location: IgLocation?,
    @SerializedName("is_video") val isVideo: Boolean,
    @SerializedName("edge_media_to_comment") val edgeMediaToComment: IgEdge,
    @SerializedName("taken_at_timestamp") val takenAtTimestamp: Long,
    @SerializedName("edge_liked_by") val edgeLikedBy: IgEdge,
    @SerializedName("thumbnail_src") val thumbnailSrc: String,
    @SerializedName("display_url") val displayUrl: String,
    @SerializedName("edge_media_to_caption") val edgeMediaToCaption: IgEdge
)

data class IgLocation(
    val id: String,
    val name: String,
    val slug: String
)
