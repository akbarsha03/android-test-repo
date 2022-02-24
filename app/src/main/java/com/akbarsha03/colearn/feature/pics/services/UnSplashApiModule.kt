package com.akbarsha03.colearn.feature.pics.services

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UnSplashApiModule {
    @Provides
    @Singleton
    fun provideApi(
        retrofit: Retrofit
    ): UnSplashApi {
        return retrofit.create(UnSplashApi::class.java)
    }
}