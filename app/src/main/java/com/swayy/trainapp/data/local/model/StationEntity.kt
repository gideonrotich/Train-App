package com.swayy.trainapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "station_table")
data class StationEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val stationName: String
)
