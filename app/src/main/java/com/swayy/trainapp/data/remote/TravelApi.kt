package com.swayy.trainapp.data.remote

import com.swayy.trainapp.domain.model.StationsResponse
import com.swayy.trainapp.domain.model.StationsResponseItem
import com.swayy.trainapp.domain.model.TravelResponseItem
import retrofit2.http.GET

interface TravelApi {

    @GET("bookings")
    suspend fun getRemoteTravel(): TravelResponseItem

    @GET("stations")
    suspend fun getStations(): StationsResponse

}