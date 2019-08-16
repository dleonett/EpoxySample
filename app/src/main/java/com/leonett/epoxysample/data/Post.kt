package com.leonett.epoxysample.data

data class Post(var id: Int, var imgUrl: String?, var title: String?, var subtitle: String?, var avatarUrl: String?, var username: String?) {
    companion object {
        fun generateList(): List<Post> {
            val items = ArrayList<Post>()

            items.add(Post(1, "https://picsum.photos/id/1011/1000/1000", "Let\'s take a ride 🛶🛶🛶", "23 comments", "https://i.pravatar.cc/150?img=9", "snickerscarrion"))
            items.add(Post(2, "https://picsum.photos/id/1012/1000/1000", "My best friend 🐶", "16 comments", "https://i.pravatar.cc/150?img=14", "rnacaddie"))
            items.add(Post(3, "https://picsum.photos/id/1013/1000/1000", "Annie\'s wedding... Lovely!", "4 comments", "https://i.pravatar.cc/150?img=10", "ditchmontie"))
            items.add(Post(4, "https://picsum.photos/id/1014/1000/1000", "It\'s not about what you see but what you feel", "9 comments", "https://i.pravatar.cc/150?img=17", "nosegrab"))
            items.add(Post(5, "https://picsum.photos/id/1015/1000/1000", "River!!", "0 comments", "https://i.pravatar.cc/150?img=11", "elfcoffee"))
            items.add(Post(6, "https://picsum.photos/id/1016/1000/1000", "🔥🔥", "11 comments", "https://i.pravatar.cc/150?img=11", "elfcoffee"))
            items.add(Post(7, "https://picsum.photos/id/1018/1000/1000", "A very peaceful place!", "7 comments", "https://i.pravatar.cc/150?img=7", "awhilesuccessful"))

            return items
        }
    }
}