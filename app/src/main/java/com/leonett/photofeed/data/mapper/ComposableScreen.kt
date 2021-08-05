package com.leonett.photofeed.data.mapper

// region DTOs
data class ComposableScreen(
    val topBar: TopBar? = null,
    val floatingAction: FloatingAction? = null,
    val sections: List<Section>
)

data class Icon(val iconId: String, val action: Action? = null)

data class TopBar(
    val title: String? = null,
    val navIcon: Icon? = null,
    val actions: List<Icon>? = null
)

data class FloatingAction(val text: String, val iconId: String? = null, val action: Action? = null)

class Action(
    val type: String, // deeplink | action
    val uri: String?,
    val id: String? = null
) {
    companion object {
        const val TYPE_ACTION = "action"
        const val TYPE_DEEPLINK = "deeplink"
    }
}

class Title(val text: String, val action: Action? = null)

class ActivityCard(val title: String, val subtitle: String, val action: Action? = null)

class Contact(
    val id: Int,
    val avatarUrl: String,
    val username: String,
    val type: String,
    val action: Action? = null
) {
    companion object {
        fun generateDummyList(): List<Contact> {
            return listOf(
                Contact(
                    1,
                    "https://i.pravatar.cc/150?img=7",
                    "Jose Tovar",
                    "local"
                ),
                Contact(
                    2,
                    "https://i.pravatar.cc/150?img=8",
                    "Alejandro Martinez",
                    "bank"
                ),
                Contact(
                    3,
                    "https://i.pravatar.cc/150?img=9",
                    "María Sanchez",
                    "none"
                ),
                Contact(
                    2,
                    "https://i.pravatar.cc/150?img=10",
                    "Camila López",
                    "local"
                ),
                Contact(
                    3,
                    "https://i.pravatar.cc/150?img=11",
                    "David Serrano",
                    "none"
                ),
                Contact(
                    3,
                    "https://i.pravatar.cc/150?img=12",
                    "Juan Valdez",
                    "none"
                ),
                Contact(
                    3,
                    "https://i.pravatar.cc/150?img=13",
                    "Cristian Suarez",
                    "bank"
                ),
                Contact(
                    3,
                    "https://i.pravatar.cc/150?img=14",
                    "John Silver",
                    "none"
                ),
                Contact(
                    3,
                    "https://i.pravatar.cc/150?img=15",
                    "Oscar Pérez",
                    "none"
                )
            )
        }

        fun mock() = Contact(
            1,
            "https://i.pravatar.cc/150?img=7",
            "Camila Lopez",
            "local"
        )
    }
}
// endregion

// region Sections
class RecentContactsSection(
    val title: Title,
    val viewAllTitle: Title?,
    val contacts: List<Contact>
) : Section()

class ActivitiesSection(val title: Title, val items: List<ActivityCard>) : Section()
// endregion