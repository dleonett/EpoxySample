package com.leonett.photofeed.data.mapper

import com.leonett.photofeed.util.RuntimeTypeAdapterFactory


object SectionsMapper {

    fun createContainerAdapterFactory(): RuntimeTypeAdapterFactory<Container> =
        RuntimeTypeAdapterFactory.of(Container::class.java, "id", true)
            .registerSubtype(TopBarContainer::class.java, "top_bar")

    fun createSectionAdapterFactory(): RuntimeTypeAdapterFactory<Section> =
        RuntimeTypeAdapterFactory.of(Section::class.java, "id", true)
            .registerSubtype(NavIconSection::class.java, "nav_icon")
            .registerSubtype(ProfileIconSection::class.java, "profile_icon")
            .registerSubtype(ShareIconSection::class.java, "share_icon")
            .registerSubtype(RefreshIconSection::class.java, "refresh_icon")
            .registerSubtype(RecentContactsSection::class.java, "recent_contacts")
            .registerSubtype(ActivitiesSection::class.java, "activities")

}
