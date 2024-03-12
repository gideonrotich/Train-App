package com.swayy.trainapp.data.mapper

import com.swayy.trainapp.data.local.model.StationEntity
import com.swayy.trainapp.data.local.model.TravelEntity
import com.swayy.trainapp.domain.model.Station
import com.swayy.trainapp.domain.model.StationsResponseItem
import com.swayy.trainapp.domain.model.Travel
import com.swayy.trainapp.domain.model.TravelResponseItemItem

internal fun TravelEntity.toDomain(): Travel {
    return Travel(
        exitStation, passengerName, startStation
    )
}

internal fun TravelResponseItemItem.toEntity(): TravelEntity {
    return TravelEntity(
        exitStation, passengerName, startStation
    )
}

internal fun StationEntity.toStationDomain():Station{
    return Station(
        id, stationName
    )
}
//
internal fun StationsResponseItem.toStationEntity(): StationEntity {
    return StationEntity(
        id, stationName
    )
}




