package com.pachuho.lol.data.di

import com.pachuho.lol.data.repository.LOLRepository
import com.pachuho.lol.data.repository.LOLRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideLOLRepository(lolRepositoryImpl: LOLRepositoryImpl): LOLRepository
}