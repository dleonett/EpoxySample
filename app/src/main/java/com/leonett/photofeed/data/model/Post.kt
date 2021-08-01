package com.leonett.photofeed.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Post(
    @SerializedName("id")
    @PrimaryKey
    var id: String,
    @SerializedName("imgUrl")
    var imgUrl: String?,
    @SerializedName("thumbnailUrl")
    var thumbnailUrl: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("avatarUrl")
    var avatarUrl: String?,
    @SerializedName("location")
    var location: String?,
    @SerializedName("username")
    var username: String?,
    var likes: Int,
    var comments: Int,
    var timeAgo: String,
    var likedByMe: Boolean,
    var userId: String
) : Serializable {

    companion object {
        fun generateDummyList(): List<Post> {
            val items = ArrayList<Post>()

            items.add(
                Post(
                    "1",
                    "https://picsum.photos/id/1011/1000/1000",
                    "https://picsum.photos/id/1011/1000/1000",
                    "Let\'s take a ride 🛶🛶🛶",
                    "https://i.pravatar.cc/150?img=9",
                    "Planet Earth",
                    "snickerscarrion",
                    20,
                    23,
                    "5 minutes ago",
                    false,
                    "3"
                )
            )
            items.add(
                Post(
                    "2",
                    "https://picsum.photos/id/1012/1000/1000",
                    "https://picsum.photos/id/1012/1000/1000",
                    "My best friend 🐶",
                    "https://i.pravatar.cc/150?img=14",
                    null,
                    "rnacaddie",
                    92,
                    16,
                    "5 minutes ago",
                    false,
                    "8"
                )
            )
            items.add(
                Post(
                    "3",
                    "https://picsum.photos/id/1013/1000/1000",
                    "https://picsum.photos/id/1013/1000/1000",
                    "Annie\'s wedding... Lovely!",
                    "https://i.pravatar.cc/150?img=10",
                    null,
                    "ditchmontie",
                    55,
                    4,
                    "5 minutes ago",
                    false,
                    "4"
                )
            )
            items.add(
                Post(
                    "4",
                    "https://picsum.photos/id/1014/1000/1000",
                    "https://picsum.photos/id/1014/1000/1000",
                    "It\'s not about what you see but what you feel",
                    "https://i.pravatar.cc/150?img=17",
                    null,
                    "nosegrab",
                    15,
                    9,
                    "5 minutes ago",
                    true,
                    "11"
                )
            )
            items.add(
                Post(
                    "5",
                    "https://picsum.photos/id/1015/1000/1000",
                    "https://picsum.photos/id/1015/1000/1000",
                    "River!!",
                    "https://i.pravatar.cc/150?img=11",
                    null,
                    "elfcoffee",
                    69,
                    0,
                    "5 minutes ago",
                    false,
                    "5"
                )
            )
            items.add(
                Post(
                    "6",
                    "https://picsum.photos/id/1016/1000/1000",
                    "https://picsum.photos/id/1016/1000/1000",
                    "🔥🔥",
                    "https://i.pravatar.cc/150?img=11",
                    null,
                    "elfcoffee",
                    196,
                    11,
                    "5 minutes ago",
                    true,
                    "5"
                )
            )
            items.add(
                Post(
                    "7",
                    "https://picsum.photos/id/1018/1000/1000",
                    "https://picsum.photos/id/1018/1000/1000",
                    "A very peaceful place!",
                    "https://i.pravatar.cc/150?img=7",
                    null,
                    "awhilesuccessful",
                    231,
                    7,
                    "5 minutes ago",
                    false,
                    "1"
                )
            )
            items.add(
                Post(
                    "8",
                    "https://picsum.photos/id/1020/1000/1000",
                    "https://picsum.photos/id/1020/1000/1000",
                    "Those little bears!",
                    "https://i.pravatar.cc/150?img=10",
                    null,
                    "ditchmontie",
                    5,
                    9,
                    "5 minutes ago",
                    false,
                    "4"
                )
            )
            items.add(
                Post(
                    "9",
                    "https://picsum.photos/id/1021/1000/1000",
                    "https://picsum.photos/id/1021/1000/1000",
                    "Lost in the fog",
                    "https://i.pravatar.cc/150?img=9",
                    null,
                    "snickerscarrion",
                    87,
                    0,
                    "5 minutes ago",
                    false,
                    "3"
                )
            )
            items.add(
                Post(
                    "10",
                    "https://picsum.photos/id/1026/1000/1000",
                    "https://picsum.photos/id/1026/1000/1000",
                    "🚂🚂🚂",
                    "https://i.pravatar.cc/150?img=9",
                    null,
                    "snickerscarrion",
                    120,
                    11,
                    "5 minutes ago",
                    true,
                    "3"
                )
            )
            items.add(
                Post(
                    "11",
                    "https://picsum.photos/id/1027/1000/1000",
                    "https://picsum.photos/id/1027/1000/1000",
                    "Shooting portraits, let\'s do this",
                    "https://i.pravatar.cc/150?img=17",
                    null,
                    "nosegrab",
                    56,
                    7,
                    "5 minutes ago",
                    true,
                    "11"
                )
            )

            return items
        }

        fun mock() = Post(
            "1",
            "https://picsum.photos/id/1011/1000/1000",
            "https://picsum.photos/id/1011/1000/1000",
            "Let\'s take a ride 🛶🛶🛶",
            "https://i.pravatar.cc/150?img=9",
            null,
            "snickerscarrion",
            20,
            23,
            "5 minutes ago",
            false,
            "3"
        )
    }

}
