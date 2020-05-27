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
    var username: String?,
    var userId: Int
) : Serializable {

    companion object {
        fun generateDummyList(): List<Post> {
            val items = ArrayList<Post>()

            items.add(
                Post(
                    1,
                    "https://picsum.photos/id/1011/1000/1000",
                    "Let\'s take a ride 🛶🛶🛶",
                    "23 comments",
                    "https://i.pravatar.cc/150?img=9",
                    "snickerscarrion",
                    3
                )
            )
            items.add(
                Post(
                    2,
                    "https://picsum.photos/id/1012/1000/1000",
                    "My best friend 🐶",
                    "16 comments",
                    "https://i.pravatar.cc/150?img=14",
                    "rnacaddie",
                    8
                )
            )
            items.add(
                Post(
                    3,
                    "https://picsum.photos/id/1013/1000/1000",
                    "Annie\'s wedding... Lovely!",
                    "4 comments",
                    "https://i.pravatar.cc/150?img=10",
                    "ditchmontie",
                    4
                )
            )
            items.add(
                Post(
                    4,
                    "https://picsum.photos/id/1014/1000/1000",
                    "It\'s not about what you see but what you feel",
                    "9 comments",
                    "https://i.pravatar.cc/150?img=17",
                    "nosegrab",
                    11
                )
            )
            items.add(
                Post(
                    5,
                    "https://picsum.photos/id/1015/1000/1000",
                    "River!!",
                    "0 comments",
                    "https://i.pravatar.cc/150?img=11",
                    "elfcoffee",
                    5
                )
            )
            items.add(
                Post(
                    6,
                    "https://picsum.photos/id/1016/1000/1000",
                    "🔥🔥",
                    "11 comments",
                    "https://i.pravatar.cc/150?img=11",
                    "elfcoffee",
                    5
                )
            )
            items.add(
                Post(
                    7,
                    "https://picsum.photos/id/1018/1000/1000",
                    "A very peaceful place!",
                    "7 comments",
                    "https://i.pravatar.cc/150?img=7",
                    "awhilesuccessful",
                    1
                )
            )
            items.add(
                Post(
                    8,
                    "https://picsum.photos/id/1020/1000/1000",
                    "Those little bears!",
                    "9 comments",
                    "https://i.pravatar.cc/150?img=10",
                    "ditchmontie",
                    4
                )
            )
            items.add(
                Post(
                    9,
                    "https://picsum.photos/id/1021/1000/1000",
                    "Lost in the fog",
                    "0 comments",
                    "https://i.pravatar.cc/150?img=9",
                    "snickerscarrion",
                    3
                )
            )
            items.add(
                Post(
                    10,
                    "https://picsum.photos/id/1026/1000/1000",
                    "🚂🚂🚂",
                    "11 comments",
                    "https://i.pravatar.cc/150?img=9",
                    "snickerscarrion",
                    3
                )
            )
            items.add(
                Post(
                    11,
                    "https://picsum.photos/id/1027/1000/1000",
                    "Shooting portraits, let\'s do this",
                    "7 comments",
                    "https://i.pravatar.cc/150?img=17",
                    "nosegrab",
                    11
                )
            )

            return items
        }
    }

}
