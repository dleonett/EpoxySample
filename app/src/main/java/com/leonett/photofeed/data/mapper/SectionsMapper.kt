package com.leonett.photofeed.data.mapper

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.leonett.photofeed.util.RuntimeTypeAdapterFactory


object SectionsMapper {

    fun getSectionsListFromJson(jsonResponse: String): List<Section> {
        val sectionsAdapterFactory =
            RuntimeTypeAdapterFactory.of(Section::class.java, "code", true)
                .registerSubtype(Section001::class.java, "001")
                .registerSubtype(Section002::class.java, "002")
                .registerSubtype(Section003::class.java, "003")
                .registerSubtype(Section004::class.java, "004")
                .registerSubtype(Section005::class.java, "005")
                .registerSubtype(Section006::class.java, "006")
                .registerSubtype(Section007::class.java, "007")
                .registerSubtype(Section008::class.java, "008")
                .registerSubtype(Section009::class.java, "009")
                .registerSubtype(Section010::class.java, "010")
                .registerSubtype(Section011::class.java, "011")
                .registerSubtype(Section012::class.java, "012")

        return (GsonBuilder()
            .registerTypeAdapterFactory(sectionsAdapterFactory)
            .create()
            .fromJson(
                jsonResponse,
                object : TypeToken<List<Section?>>() {}.type
            ) as List<Section?>).filterNotNull()
    }

    fun createAdapterFactory(): RuntimeTypeAdapterFactory<Section> =
        RuntimeTypeAdapterFactory.of(Section::class.java, "id", true)
            .registerSubtype(RecentContactsSection::class.java, "recent_contacts")

}

open class Section {
    val id: String? = null
    val code: String? = null
    val spanCount: Int = 1
    var sections: List<Section>? = null
    var action: Action? = null

    override fun toString(): String {
        return Gson().toJson(this)
    }

    class Action(
        val type: String,
        val uri: String?,
        val code: String?
    ) {
        companion object {
            const val TYPE_ACTION = "action"
            const val TYPE_DEEPLINK = "deeplink"
        }
    }
}

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

class FloatingAction(val text: String, val iconId: String? = null, val action: Action? = null)

class Title(val text: String, val action: Action? = null)

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

class RecentContactsSection(
    val title: Title,
    val viewAllTitle: Title?,
    val contacts: List<Contact>
) : Section()

// region Code-based sections
class Section001 : Section() {
    val title: String? = null
    val description: String? = null
}

class Section002 : Section() {
    val title: String? = null
    val buttonText: String? = null
}

class Section003 : Section() {
    val title: String? = null
    val description: String? = null
    val imageUrl: String? = null
}

class Section004 : Section() {
    val imageUrl: String? = null
}

class Section005 : Section() {
    val buttonText: String? = null
}

class Section006 : Section()

class Section007 : Section() {
    val spanSize: Int = 1
}

class Section008 : Section() {
    val imageUrl: String? = null
    val title: String? = null
}

class Section009 : Section()

class Section010 : Section() {
    val description: String? = null
    val imageUrl: String? = null
}

class Section011 : Section() {
    val imageUrl: String? = null
    val title: String? = null
}

class Section012 : Section() {
    val imageUrl: String? = null
    val title: String? = null
}
// endregion