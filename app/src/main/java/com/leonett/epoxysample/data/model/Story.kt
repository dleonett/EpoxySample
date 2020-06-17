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
                    "awhilesuccessful",
                    1
                )
            )
            items.add(
                Story(
                    2,
                    "https://i.pravatar.cc/150?img=8",
                    "firechef",
                    2
                )
            )
            items.add(
                Story(
                    3,
                    "https://i.pravatar.cc/150?img=9",
                    "snickerscarrion",
                    3
                )
            )
            items.add(
                Story(
                    4,
                    "https://i.pravatar.cc/150?img=10",
                    "ditchmontie",
                    4
                )
            )
            items.add(
                Story(
                    5,
                    "https://i.pravatar.cc/150?img=11",
                    "elfcoffee",
                    5
                )
            )
            items.add(
                Story(
                    6,
                    "https://i.pravatar.cc/150?img=12",
                    "scrabblebased",
                    6
                )
            )
            items.add(
                Story(
                    7,
                    "https://i.pravatar.cc/150?img=13",
                    "taskspiritual",
                    7
                )
            )
            items.add(
                Story(
                    8,
                    "https://i.pravatar.cc/150?img=14",
                    "rnacaddie",
                    8
                )
            )
            items.add(
                Story(
                    9,
                    "https://i.pravatar.cc/150?img=15",
                    "repeatsavior",
                    9
                )
            )
            items.add(
                Story(
                    10,
                    "https://i.pravatar.cc/150?img=16",
                    "superstoreaspen",
                    10
                )
            )
            items.add(
                Story(
                    11,
                    "https://i.pravatar.cc/150?img=17",
                    "nosegrab",
                    11
                )
            )

            return items
        }
    }

}