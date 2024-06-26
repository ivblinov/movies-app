package com.examples.moviesapp.di

import com.examples.moviesapp.data.api.KinoService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://kinopoiskapiunofficial.tech/"

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitService(
        moshiConverterFactory: MoshiConverterFactory
    ): KinoService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(moshiConverterFactory)
            .build()
            .create(KinoService::class.java)
    }

    @Provides
    fun provideMoshiConverterFactory(): MoshiConverterFactory {
        return MoshiConverterFactory.create()
    }
}