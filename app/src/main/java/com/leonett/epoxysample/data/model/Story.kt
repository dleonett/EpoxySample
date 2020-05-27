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
) : Serializable {

    companion object {
        fun generateDummyList(): List<Story> {
            val items = ArrayList<Story>()

            items.add(
                Story(
                    1,
                    "https://i.pravatar.cc/150?img=7",
                    "awhilesuccessful"
                )
            )
            items.add(
                Story(
                    2,
                    "https://i.pravatar.cc/150?img=8",
                    "firechef"
                )
            )
            items.add(
                Story(
                    3,
                    "https://i.pravatar.cc/150?img=9",
                    "snickerscarrion"
                )
            )
            items.add(
                Story(
                    4,
                    "https://i.pravatar.cc/150?img=10",
                    "ditchmontie"
                )
            )
            items.add(
                Story(
                    5,
                    "https://i.pravatar.cc/150?img=11",
                    "elfcoffee"
                )
            )
            items.add(
                Story(
                    6,
                    "https://i.pravatar.cc/150?img=12",
                    "scrabblebased"
                )
            )
            items.add(
                Story(
                    7,
                    "https://i.pravatar.cc/150?img=13",
                    "taskspiritual"
                )
            )
            items.add(
                Story(
                    8,
                    "https://i.pravatar.cc/150?img=14",
                    "rnacaddie"
                )
            )
            items.add(
                Story(
                    9,
                    "https://i.pravatar.cc/150?img=15",
                    "repeatsavior"
                )
            )
            items.add(
                Story(
                    10,
                    "https://i.pravatar.cc/150?img=16",
                    "superstoreaspen"
                )
            )
            items.add(
                Story(
                    11,
                    "https://i.pravatar.cc/150?img=17",
                    "nosegrab"
                )
            )

            return items
        }
    }

}