package com.excellentwebworld.demodaggerhiltdi.repository

import com.excellentwebworld.demodaggerhiltdi.model.KanyeWestApiResponse
import com.excellentwebworld.demodaggerhiltdi.model.Resource

interface MainRepository {
    suspend fun getQuotes(): Resource<KanyeWestApiResponse>
}