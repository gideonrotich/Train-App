package com.swayy.trainapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.swayy.trainapp.data.local.model.TravelEntity

@Dao
interface TravelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTravel(travelEntity: List<TravelEntity>)

    @Query("SELECT * FROM travel_table")
    fun getTravel(): List<TravelEntity>

    @Query("DELETE FROM travel_table")
    suspend fun deleteAllTravels()

}