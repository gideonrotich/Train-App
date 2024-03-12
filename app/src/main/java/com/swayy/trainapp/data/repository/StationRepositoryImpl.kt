package com.swayy.trainapp.data.repository

import android.content.Context
import com.swayy.trainapp.data.local.dao.StationDao
import com.swayy.trainapp.data.local.dao.TravelDao
import com.swayy.trainapp.data.mapper.toDomain
import com.swayy.trainapp.data.mapper.toEntity
import com.swayy.trainapp.data.mapper.toStationDomain
import com.swayy.trainapp.data.mapper.toStationEntity
import com.swayy.trainapp.data.remote.TravelApi
import com.swayy.trainapp.domain.model.Station
import com.swayy.trainapp.domain.repository.StationRepository
import com.swayy.trainapp.util.Resource
import com.swayy.trainapp.util.safeApiCall
import kotlinx.coroutines.Dispatchers

class StationRepositoryImpl(
    private val travelApi: TravelApi,
    private val stationDao: StationDao,
) : StationRepository {
    override suspend fun getStations(): Resource<ArrayList<Station>> {
        return safeApiCall(Dispatchers.IO) {
            stationDao.deleteAllStations()
            val remoteData = travelApi.getStations()

            stationDao.insertStation(remoteData.map { it.toStationEntity() })

            val allTravel = stationDao.getStation().map { it.toStationDomain() }

            ArrayList(allTravel)
        }
    }
}