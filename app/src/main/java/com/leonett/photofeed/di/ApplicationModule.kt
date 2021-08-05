package com.leonett.photofeed.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.leonett.photofeed.data.mapper.SectionsMapper
import com.leonett.photofeed.data.source.AppDatabase
import com.leonett.photofeed.data.source.PostsApiService
import com.leonett.photofeed.data.source.ScreensApiService
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

        val sectionsAdapterFactory = SectionsMapper.createAdapterFactory()

        val gson = (GsonBuilder()
            .registerTypeAdapterFactory(sectionsAdapterFactory)).create()

        return Retrofit.Builder()
            .baseUrl("https://610b2cec52d56400176b0131.mockapi.io/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun providePostsApiService(retrofit: Retrofit): PostsApiService {
        return retrofit.create(PostsApiService::class.java)
    }

    @Provides
    fun provideScreensApiService(retrofit: Retrofit): ScreensApiService {
        return retrofit.create(ScreensApiService::class.java)
    }

    @Provides
    @AppScope
    fun provideAppDataBase(): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "posts-db"
        ).build()
    }

    @Provides
    fun provideSharedPreferences(): SharedPreferences {
        return applicationContext.getSharedPreferences("user-prefs", Context.MODE_PRIVATE)
    }

}