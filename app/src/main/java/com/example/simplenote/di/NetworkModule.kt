package com.example.simplenote.di

import com.example.simplenote.data.service.FirebaseService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideLoginService(): FirebaseService {
        return FirebaseService()
    }
}