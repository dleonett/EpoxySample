package com.leonett.photofeed.data.mapper

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class SectionsMapper {

    fun getSectionsList(responseJson: String): List<Section> {
        val deserializer = SectionDeserializer("code")
        deserializer.registerBarnType("001", Section001::class.java)
        deserializer.registerBarnType("002", Section002::class.java)
        deserializer.registerBarnType("003", Section003::class.java)
        deserializer.registerBarnType("004", Section004::class.java)

        return (GsonBuilder()
            .registerTypeAdapter(Section::class.java, deserializer)
            .create()
            .fromJson(
                responseJson,
                object : TypeToken<List<Section?>?>() {}.type
            ) as List<Section?>).filterNotNull()
    }

}

class SectionDeserializer(private val sectionTypeElementName: String) :
    JsonDeserializer<Section?> {

    private val gson: Gson = Gson()
    private val sectionTypeRegistry: MutableMap<String, Class<out Section>>

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
        return gson.fromJson(sectionObject, sectionType)
    }

    init {
        sectionTypeRegistry = HashMap()
    }
}

open class Section {
    val code: String? = null
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