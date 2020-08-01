package com.leonett.photofeed.data.mapper

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import com.leonett.photofeed.util.RuntimeTypeAdapterFactory
import java.lang.reflect.Type


class SectionsMapper {

    fun getSectionsList(responseJson: String): List<Section> {
        val deserializer = SectionDeserializer("code")
        deserializer.registerBarnType("001", Section001::class.java)
        deserializer.registerBarnType("002", Section002::class.java)
        deserializer.registerBarnType("003", Section003::class.java)
        deserializer.registerBarnType("004", Section004::class.java)
        deserializer.registerBarnType("005", Section005::class.java)
        deserializer.registerBarnType("006", Section006::class.java)
        deserializer.registerBarnType("007", Section007::class.java)
        deserializer.registerBarnType("008", Section008::class.java)

        return (GsonBuilder()
            .registerTypeAdapter(Section::class.java, deserializer)
            .create()
            .fromJson(
                responseJson,
                object : TypeToken<List<Section?>?>() {}.type
            ) as List<Section?>).filterNotNull()
    }

    fun getSectionsListAlt(responseJson: String): List<Section> {
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

        return (GsonBuilder()
            .registerTypeAdapterFactory(sectionsAdapterFactory)
            .create()
            .fromJson(
                responseJson,
                object : TypeToken<List<Section?>>() {}.type
            ) as List<Section?>).filterNotNull()
    }

}

class SectionDeserializer(private val sectionTypeElementName: String) :
    JsonDeserializer<Section?> {

    private val gson = Gson()
    private val sectionsMapper = SectionsMapper()
    private val sectionTypeRegistry: MutableMap<String, Class<out Section>> = HashMap()

    fun registerBarnType(
        sectionTypeName: String,
        sectionType: Class<out Section>
    ) {
        sectionTypeRegistry[sectionTypeName] = sectionType
    }

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Section? {
        val sectionObject = json.asJsonObject
        val sectionTypeElement = sectionObject[sectionTypeElementName]
        val sectionType =
            sectionTypeRegistry[sectionTypeElement.asString] ?: return null
        val section = gson.fromJson(sectionObject, sectionType)

        if (sectionObject.has("sections")) {
            section.sections =
                sectionsMapper.getSectionsList(
                    sectionObject.getAsJsonArray("sections").toString()
                )
        }

        return section
    }
}

open class Section {
    val code: String? = null
    val spanCount: Int = 1
    var sections: List<Section>? = null
}

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