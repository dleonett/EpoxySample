package com.leonett.epoxysample.di

import com.leonett.epoxysample.data.source.PostsApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApplicationModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
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

}