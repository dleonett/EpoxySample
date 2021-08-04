package com.leonett.photofeed.data.model

import java.io.Serializable

data class Contact(
    var id: Int,
    var avatarUrl: String,
    var username: String,
    var type: ContactType
) : Serializable {

    enum class ContactType {
        NONE, LOCAL, BANK
    }

    companion object {
        fun generateDummyList(): List<Contact> {
            return listOf(
                Contact(
                    1,
                    "https://i.pravatar.cc/150?img=7",
                    "Jose Tovar",
                    ContactType.LOCAL
                ),
                Contact(
                    2,
                    "https://i.pravatar.cc/150?img=8",
                    "Alejandro Martinez",
                    ContactType.BANK
                ),
                Contact(
                    3,
                    "https://i.pravatar.cc/150?img=9",
                    "María Sanchez",
                    ContactType.NONE
                ),
                Contact(
                    2,
                    "https://i.pravatar.cc/150?img=10",
                    "Camila López",
                    ContactType.LOCAL
                ),
                Contact(
                    3,
                    "https://i.pravatar.cc/150?img=11",
                    "David Serrano",
                    ContactType.NONE
                ),
                Contact(
                    3,
                    "https://i.pravatar.cc/150?img=12",
                    "Juan Valdez",
                    ContactType.NONE
                ),
                Contact(
                    3,
                    "https://i.pravatar.cc/150?img=13",
                    "Cristian Suarez",
                    ContactType.BANK
                ),
                Contact(
                    3,
                    "https://i.pravatar.cc/150?img=14",
                    "John Silver",
                    ContactType.NONE
                ),
                Contact(
                    3,
                    "https://i.pravatar.cc/150?img=15",
                    "Oscar Pérez",
                    ContactType.NONE
                )
            )
        }

        fun mock() = Contact(
            1,
            "https://i.pravatar.cc/150?img=7",
            "Camila Lopez",
            ContactType.LOCAL
        )
    }

}