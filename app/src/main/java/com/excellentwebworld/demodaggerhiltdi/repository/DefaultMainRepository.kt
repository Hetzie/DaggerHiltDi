package com.excellentwebworld.demodaggerhiltdi.repository

import com.excellentwebworld.demodaggerhiltdi.model.KanyeWestApiResponse
import com.excellentwebworld.demodaggerhiltdi.model.Resource
import com.excellentwebworld.demodaggerhiltdi.model.api.KanyeWestApi
import javax.inject.Inject

class DefaultMainRepository @Inject constructor(
    val kanyeWestApi: KanyeWestApi
) : MainRepository {
    override suspend fun getQuotes(): Resource<KanyeWestApiResponse> {
        return try {
            val response = kanyeWestApi.getQuotes()
            val result = response.body()
            if (response.isSuccessful && response!=null){
                Resource.Success(result)
            }
            else{
                Resource.Error("Error Occurred")
            }
        }
        catch (e: Exception){
            Resource.Error("An $e Occurred")
        }
    }
}