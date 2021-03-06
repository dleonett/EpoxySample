package com.leonett.photofeed.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class User(
    @PrimaryKey
    val id: String,
    val username: String,
    val displayName: String,
    val description: String,
    val avatarUrl: String,
    val posts: Int,
    val followers: Int,
    val following: Int,
    val isPrivate: Boolean = false,
    val isVerified: Boolean = false,
    val externalUrl: String? = null
) : Serializable {

    companion object {
        fun generateDummyList(): List<User> {
            val items = mutableListOf<User>()

            items.add(
                User(
                    "1",
                    "awhilesuccessful",
                    "Tillman Veum",
                    "Upstairs is not the inward shame of the monkey.",
                    "https://i.pravatar.cc/150?img=7",
                    1,
                    100,
                    150
                )
            )
            items.add(
                User(
                    "2",
                    "firechef",
                    "Simone Wisoky",
                    "The wind fears. Blessing is the only core, the only guarantee of faith.",
                    "https://i.pravatar.cc/150?img=8",
                    0,
                    100,
                    150
                )
            )
            items.add(
                User(
                    "3",
                    "snickerscarrion",
                    "Rodolfo Runolfsdottir",
                    "When the explosion of the sorrow of result praises the solitudes of the sinner, the samadhi will know individual.",
                    "https://i.pravatar.cc/150?img=9",
                    3,
                    100,
                    150
                )
            )
            items.add(
                User(
                    "4",
                    "ditchmontie",
                    "Jewel Bins",
                    "All special seekers love each other, only unbiased moons have a light.",
                    "https://i.pravatar.cc/150?img=10",
                    2,
                    152,
                    83
                )
            )
            items.add(
                User(
                    "5",
                    "elfcoffee",
                    "Jeanie Konopelski",
                    "Ancient paradoxs handles most thoughts.\nOne must hear the lotus in order to yearn the explosion of the man of abstruse futility.",
                    "https://i.pravatar.cc/150?img=11",
                    2,
                    563,
                    902
                )
            )
            items.add(
                User(
                    "6",
                    "scrabblebased",
                    "Jessyca Zieme",
                    "Sorrow happens when you desire definition so authoratively that whatsoever you are empowering is your blessing.",
                    "https://i.pravatar.cc/150?img=12",
                    0,
                    100,
                    150
                )
            )
            items.add(
                User(
                    "7",
                    "taskspiritual",
                    "Destin Hane",
                    "Who can desire the conclusion and blessing of an aspect if he has the united harmony of the body?",
                    "https://i.pravatar.cc/150?img=13",
                    0,
                    100,
                    150
                )
            )
            items.add(
                User(
                    "8",
                    "rnacaddie",
                    "Isidro Will",
                    "You have to balance, and discover music by your failing.",
                    "https://i.pravatar.cc/150?img=14",
                    1,
                    100,
                    150
                )
            )
            items.add(
                User(
                    "9",
                    "repeatsavior",
                    "Billy Spencer",
                    "Zen, heaven and a unbiased next world.\nThe man is a soft monkey.",
                    "https://i.pravatar.cc/150?img=15",
                    0,
                    100,
                    150
                )
            )
            items.add(
                User(
                    "10",
                    "superstoreaspen",
                    "Keyshawn Mayert",
                    "Heavens of conclusion will wisely grasp an inner wind.",
                    "https://i.pravatar.cc/150?img=16",
                    0,
                    100,
                    150
                )
            )
            items.add(
                User(
                    "11",
                    "nosegrab",
                    "Cullen Bashirian",
                    "Going to the realm of enlightenment doesn’t trap reincarnation anymore than emerging creates united sex.",
                    "https://i.pravatar.cc/150?img=17",
                    2,
                    78,
                    150
                )
            )

            return items
        }
    }

}