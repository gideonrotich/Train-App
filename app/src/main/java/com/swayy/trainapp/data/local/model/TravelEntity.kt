package com.swayy.trainapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "travel_table")
data class TravelEntity(
    @PrimaryKey(autoGenerate = false)
    val exitStation: Int,
    val passengerName: String,
    val startStation: Int
)
