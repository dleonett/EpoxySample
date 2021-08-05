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
            .registerSubtype(ActivitiesSection::class.java, "activities")

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