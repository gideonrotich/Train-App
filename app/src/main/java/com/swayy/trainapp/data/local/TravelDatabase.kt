package com.swayy.trainapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.swayy.trainapp.data.local.dao.StationDao
import com.swayy.trainapp.data.local.dao.TravelDao
import com.swayy.trainapp.data.local.model.StationEntity
import com.swayy.trainapp.data.local.model.TravelEntity

@Database(
    entities = [
        TravelEntity::class,
        StationEntity::class
    ],
    version = 4,
    exportSchema = true
)
abstract class TravelDatabase : RoomDatabase(){
    abstract val travelDao: TravelDao
    abstract val stationDao: StationDao


}