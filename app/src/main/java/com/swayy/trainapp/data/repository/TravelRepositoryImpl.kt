package com.swayy.trainapp.data.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.swayy.trainapp.data.local.dao.TravelDao
import com.swayy.trainapp.data.mapper.toDomain
import com.swayy.trainapp.data.mapper.toEntity
import com.swayy.trainapp.data.remote.TravelApi
import com.swayy.trainapp.domain.model.Travel
import com.swayy.trainapp.domain.model.TravelResponseItemItem
import com.swayy.trainapp.domain.repository.TravelRepository
import com.swayy.trainapp.util.Resource
import com.swayy.trainapp.util.safeApiCall
import kotlinx.coroutines.Dispatchers

class TravelRepositoryImpl(
    private val travelApi: TravelApi,
    private val context: Context,
    private val travelDao: TravelDao,
) : TravelRepository {
    override suspend fun getTravels(): Resource<ArrayList<Travel>> {
        return safeApiCall(Dispatchers.IO) {
//            val jsonString =
//                context.assets.open("travel.json").bufferedReader().use { it.readText() }
//            val response = HouseJsonParser.parseHousesFromJson(jsonString)

            travelDao.deleteAllTravels()

            val remoteData = travelApi.getRemoteTravel()

            travelDao.insertTravel(remoteData.map { it.toEntity() })

            val allTravel = travelDao.getTravel().map { it.toDomain() }

            ArrayList(allTravel)
        }
    }
}

//object HouseJsonParser {
//    fun parseHousesFromJson(jsonString: String): List<TravelResponseItemItem> {
//        val gson = Gson()
//        val listType = object : TypeToken<List<TravelResponseItemItem>>() {}.type
//        return gson.fromJson(jsonString, listType)
//    }
//}