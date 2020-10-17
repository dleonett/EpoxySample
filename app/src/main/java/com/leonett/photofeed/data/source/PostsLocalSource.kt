package com.leonett.photofeed.data.source

import androidx.room.*
import com.leonett.photofeed.data.model.Post
import com.leonett.photofeed.data.model.Story
import com.leonett.photofeed.data.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostsLocalSource @Inject constructor(private val db: AppDatabase) {

    suspend fun insertPosts(posts: List<Post>) {
        db.postsDao().insertAll(posts)
    }

    suspend fun fetchPosts(): List<Post> {
        return db.postsDao().getAll()
    }

    fun getPostsObservable(): Flow<List<Post>> {
        return db.postsDao().getAllAsObservable()
    }

    fun getPostsByUserObservable(userId: Int): Flow<List<Post>> {
        return db.postsDao().getPostsByUserObservable(userId)
    }

    suspend fun insertStories(stories: List<Story>) {
        db.storiesDao().insertAll(stories)
    }

    suspend fun fetchStories(): List<Story> {
        return db.storiesDao().getAll()
    }

    fun getStoriesObservable(): Flow<List<Story>> {
        return db.storiesDao().getAllAsObservable()
    }

    suspend fun insertUsers(users: List<User>) {
        db.usersDao().insertAll(users)
    }

    fun getUserObservable(userId: Int): Flow<User> {
        return db.usersDao().getUserObservable(userId)
    }

    fun getPostByIdAsObservable(postId: String): Flow<Post> {
        return db.postsDao().getPostByIdAsObservable(postId)
    }

    suspend fun updatePostLike(postId: String, likedByMe: Boolean, likes: Int) {
        db.postsDao().updateLike(postId, likedByMe, likes)
    }

}

@Database(entities = [Post::class, Story::class, User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postsDao(): PostsDao
    abstract fun storiesDao(): StoriesDao
    abstract fun usersDao(): UsersDao
}

@Dao
interface PostsDao {
    @Query("SELECT * FROM Post")
    suspend fun getAll(): List<Post>

    @Query("SELECT * FROM Post WHERE userId = :userId")
    fun getPostsByUserObservable(userId: Int): Flow<List<Post>>

    @Query("SELECT * FROM Post")
    fun getAllAsObservable(): Flow<List<Post>>

    @Query("SELECT * FROM Post WHERE id = :postId")
    fun getPostByIdAsObservable(postId: String): Flow<Post>

    @Query("UPDATE post SET likedByMe = :likedByMe, likes = :likes WHERE id = :postId")
    suspend fun updateLike(postId: String, likedByMe: Boolean, likes: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<Post>)

    @Delete
    suspend fun delete(post: Post)
}

@Dao
interface UsersDao {
    @Query("SELECT * FROM User WHERE id = :userId")
    fun getUserObservable(userId: Int): Flow<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<User>)

    @Delete
    suspend fun delete(user: User)
}

@Dao
interface StoriesDao {
    @Query("SELECT * FROM Story")
    suspend fun getAll(): List<Story>

    @Query("SELECT * FROM Story")
    fun getAllAsObservable(): Flow<List<Story>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(stories: List<Story>)

    @Delete
    suspend fun delete(story: Story)
}