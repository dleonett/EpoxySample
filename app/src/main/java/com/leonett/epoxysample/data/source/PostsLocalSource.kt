package com.leonett.epoxysample.data.source

import androidx.room.*
import com.leonett.epoxysample.data.model.Post
import com.leonett.epoxysample.data.model.Story
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

    suspend fun insertStories(stories: List<Story>) {
        db.storiesDao().insertAll(stories)
    }

    suspend fun fetchStories(): List<Story> {
        return db.storiesDao().getAll()
    }

    fun getStoriesObservable(): Flow<List<Story>> {
        return db.storiesDao().getAllAsObservable()
    }

}

@Database(entities = [Post::class, Story::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postsDao(): PostsDao
    abstract fun storiesDao(): StoriesDao
}

@Dao
interface PostsDao {
    @Query("SELECT * FROM Post")
    suspend fun getAll(): List<Post>

    @Query("SELECT * FROM Post")
    fun getAllAsObservable(): Flow<List<Post>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(posts: List<Post>)

    @Delete
    suspend fun delete(post: Post)
}

@Dao
interface StoriesDao {
    @Query("SELECT * FROM Story")
    suspend fun getAll(): List<Story>

    @Query("SELECT * FROM Story")
    fun getAllAsObservable(): Flow<List<Story>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(stories: List<Story>)

    @Delete
    suspend fun delete(story: Story)
}