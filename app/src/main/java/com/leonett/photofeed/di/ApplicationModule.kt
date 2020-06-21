package com.leonett.photofeed.di

import android.content.Context
import androidx.room.Room
import com.leonett.photofeed.data.source.AppDatabase
import com.leonett.photofeed.data.source.PostsApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApplicationModule(private val applicationContext: Context) {

    @Provides
    fun provideRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl("http://www.mocky.io/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providePostsApiService(retrofit: Retrofit): PostsApiService {
        return retrofit.create(PostsApiService::class.java)
    }

    @Provides
    fun provideAppDataBase(): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "posts-db"
        ).build()
    }

}