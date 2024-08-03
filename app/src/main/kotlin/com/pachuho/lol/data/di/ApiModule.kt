package com.pachuho.lol.data.di

import com.pachuho.lol.data.interceptor.LoggerInterceptor
import com.pachuho.lol.data.remote.ChampionService
import com.pachuho.lol.data.remote.UrlConstants.BASE_URL
import com.pachuho.lol.data.remote.UrlConstants.VERSION
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggerInterceptor: LoggerInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor(loggerInterceptor).setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("${BASE_URL}/${VERSION}/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideChampionService(retrofit: Retrofit): ChampionService {
        return retrofit.create(ChampionService::class.java)
    }
}