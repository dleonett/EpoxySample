package com.leonett.epoxysample.data

data class GenericItem(var id: Int, var imgUrl: String?, var title: String?, var subtitle: String?) {
    companion object {
        fun generateItemsList(): List<GenericItem> {
            val items = ArrayList<GenericItem>()

            items.add(GenericItem(1, "https://picsum.photos/id/1011/1000/1000", "Title 1", "Subtitle one"))
            items.add(GenericItem(2, "https://picsum.photos/id/1012/1000/1000", "Title 2", "Subtitle two"))
            items.add(GenericItem(3, "https://picsum.photos/id/1013/1000/1000", "Title 3", "Subtitle three"))
            items.add(GenericItem(4, "https://picsum.photos/id/1014/1000/1000", "Title 4", "Subtitle four"))
            items.add(GenericItem(5, "https://picsum.photos/id/1015/1000/1000", "Title 5", "Subtitle five"))
            items.add(GenericItem(6, "https://picsum.photos/id/1016/1000/1000", "Title 6", "Subtitle six"))
            items.add(GenericItem(7, "https://picsum.photos/id/1018/1000/1000", "Title 7", "Subtitle seven"))

            return items
        }
    }
}