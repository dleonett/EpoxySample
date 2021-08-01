package com.leonett.photofeed.data.model

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
    var avatarUrl: String,
    @SerializedName("imgUrl")
    var imgUrl: String,
    @SerializedName("username")
    var username: String,
    var userId: Int
) : Serializable {

    companion object {
        fun generateDummyList(): List<Story> {
            val items = ArrayList<Story>()

            items.add(
                Story(
                    1,
                    "https://i.pravatar.cc/150?img=7",
                    "https://picsum.photos/id/350/1080/1920",
                    "awhilesuccessful",
                    1
                )
            )
            items.add(
                Story(
                    2,
                    "https://i.pravatar.cc/150?img=8",
                    "https://picsum.photos/id/250/1080/1920",
                    "firechef",
                    2
                )
            )
            items.add(
                Story(
                    3,
                    "https://i.pravatar.cc/150?img=9",
                    "https://picsum.photos/id/251/1080/1920",
                    "snickerscarrion",
                    3
                )
            )
            items.add(
                Story(
                    4,
                    "https://i.pravatar.cc/150?img=10",
                    "https://picsum.photos/id/252/1080/1920",
                    "ditchmontie",
                    4
                )
            )
            items.add(
                Story(
                    5,
                    "https://i.pravatar.cc/150?img=11",
                    "https://picsum.photos/id/253/1080/1920",
                    "elfcoffee",
                    5
                )
            )
            items.add(
                Story(
                    6,
                    "https://i.pravatar.cc/150?img=12",
                    "https://picsum.photos/id/254/1080/1920",
                    "scrabblebased",
                    6
                )
            )
            items.add(
                Story(
                    7,
                    "https://i.pravatar.cc/150?img=13",
                    "https://picsum.photos/id/351/1080/1920",
                    "taskspiritual",
                    7
                )
            )
            items.add(
                Story(
                    8,
                    "https://i.pravatar.cc/150?img=14",
                    "https://picsum.photos/id/352/1080/1920",
                    "rnacaddie",
                    8
                )
            )
            items.add(
                Story(
                    9,
                    "https://i.pravatar.cc/150?img=15",
                    "https://picsum.photos/id/333/1080/1920",
                    "repeatsavior",
                    9
                )
            )
            items.add(
                Story(
                    10,
                    "https://i.pravatar.cc/150?img=16",
                    "https://picsum.photos/id/345/1080/1920",
                    "superstoreaspen",
                    10
                )
            )
            items.add(
                Story(
                    11,
                    "https://i.pravatar.cc/150?img=17",
                    "https://picsum.photos/id/320/1080/1920",
                    "nosegrab",
                    11
                )
            )

            return items
        }

        fun mock() = Story(
            1,
            "https://i.pravatar.cc/150?img=7",
            "https://picsum.photos/id/350/1080/1920",
            "awhilesuccessful",
            1
        )
    }

}