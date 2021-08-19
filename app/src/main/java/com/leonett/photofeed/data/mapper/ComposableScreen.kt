package com.leonett.photofeed.data.mapper

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

// region DTOs
data class ComposableScreen(
    val id: String? = null,
    @SerializedName("units")
    val containers: List<Container>,
)

// region Containers
open class Container(
    val id: String? = null,
    val title: String? = null,
    val status: Boolean? = null,
    val sections: List<Section>? = null
)

class TopBarContainer() : Container()
class MainContentContainer() : Container()
// endregion

data class Icon(val iconId: String)


data class FloatingAction(val text: String, val iconId: String? = null, val action: Action? = null)

class Action(
    val type: String, // deeplink | action
    val uri: String?
) {
    companion object {
        const val TYPE_ACTION = "action"
        const val TYPE_DEEPLINK = "deeplink"
    }
}

class Title(val text: String, val action: Action? = null)

class ActivityCard(val title: String, val subtitle: String, val action: Action? = null) {
    companion object {
        fun generateDummyList(): List<ActivityCard> {
            return listOf(
                ActivityCard(
                    "Cumple Rolo",
                    "21 agosto 2021",
                ),
                ActivityCard(
                    "Salida viernes",
                    "20 agosto 2021",
                ),
                ActivityCard(
                    "Vuelta al mundo",
                    "20 agosto 2021",
                ),
                ActivityCard(
                    "Fútbol con los del trabajo",
                    "19 agosto 2021",
                ),
                ActivityCard(
                    "Cena con los amigos",
                    "11 agosto 2021",
                ),
                ActivityCard(
                    "Cumpleaños de Fabiola",
                    "10 junio 2021",
                ),
                ActivityCard(
                    "Fiesta donde mi hermano",
                    "1 junio 2021",
                )
            )
        }
    }
}

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
open class Section {
    val id: String? = null
    val title: String? = null
    var sections: List<Section>? = null
    val action: Action? = null

    override fun toString(): String {
        return Gson().toJson(this)
    }
}

class NavIconSection() : Section()
class ProfileIconSection() : Section()
class ShareIconSection() : Section()
class RefreshIconSection() : Section()

class RecentContactsSection(
    val viewAllTitle: Title?,
    val contacts: List<ContactSection>
) : Section()

class ContactSection(
    val contact: Contact
) : Section()

class ActivitiesSection(val items: List<ActivitySection>) : Section()
class ActivitySection(val activity: ActivityCard) : Section()
// endregion