package com.swayy.trainapp.domain.repository

import com.swayy.trainapp.domain.model.Station
import com.swayy.trainapp.domain.model.Travel
import com.swayy.trainapp.util.Resource

interface StationRepository {
    suspend fun getStations(): Resource<ArrayList<Station>>
}