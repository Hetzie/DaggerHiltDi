package com.excellentwebworld.demodaggerhiltdi

import com.excellentwebworld.demodaggerhiltdi.model.api.KanyeWestApi
import com.excellentwebworld.demodaggerhiltdi.repository.DefaultMainRepository
import com.excellentwebworld.demodaggerhiltdi.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://api.kanye.rest/"

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideKanyaWestApi(): KanyeWestApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(KanyeWestApi::class.java)

    @Singleton
    @Provides
    fun provideRepository(kanyeWestApi: KanyeWestApi) : MainRepository = DefaultMainRepository(kanyeWestApi)
}