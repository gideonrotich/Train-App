package com.swayy.trainapp.domain.repository

import com.swayy.trainapp.domain.model.Travel
import com.swayy.trainapp.util.Resource

interface TravelRepository {
    suspend fun getTravels(): Resource<ArrayList<Travel>>
}