package com.swayy.trainapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.swayy.trainapp.data.local.model.StationEntity
import com.swayy.trainapp.data.local.model.TravelEntity

@Dao
interface StationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStation(stationEntity: List<StationEntity>)

    @Query("SELECT * FROM station_table")
    fun getStation(): List<StationEntity>

    @Query("DELETE FROM station_table")
    suspend fun deleteAllStations()
}