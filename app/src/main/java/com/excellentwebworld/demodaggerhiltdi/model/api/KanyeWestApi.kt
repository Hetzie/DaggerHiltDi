package com.excellentwebworld.demodaggerhiltdi.model.api

import com.excellentwebworld.demodaggerhiltdi.model.KanyeWestApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface KanyeWestApi {
    @GET(".")
    suspend fun getQuotes(): Response<KanyeWestApiResponse>
}