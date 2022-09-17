package com.hackzurich.hackzurich22.di

import com.google.android.datatransport.runtime.dagger.Provides
import com.hackzurich.hackzurich22.network.WatAppService
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            //Todo: Add the correct base url
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideWatAppService(retrofit: Retrofit): WatAppService {
        return retrofit.create(WatAppService::class.java)
    }
}