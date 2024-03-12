package com.swayy.trainapp.di

import android.content.Context
import androidx.room.Room
import com.swayy.trainapp.data.local.TravelDatabase
import com.swayy.trainapp.data.local.dao.StationDao
import com.swayy.trainapp.data.local.dao.TravelDao
import com.swayy.trainapp.data.remote.Constants
import com.swayy.trainapp.data.remote.TravelApi
import com.swayy.trainapp.data.repository.StationRepositoryImpl
import com.swayy.trainapp.data.repository.TravelRepositoryImpl
import com.swayy.trainapp.domain.repository.StationRepository
import com.swayy.trainapp.domain.repository.TravelRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHarryPotterApi(okHttpClient: OkHttpClient): TravelApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(TravelApi::class.java)
    }

    @Provides
    @Singleton
    fun provideApplicationContext(
        @ApplicationContext context: Context
    ): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideTravelRepository(
        travelApi: TravelApi,
        context: Context,
        travelDao: TravelDao
    ): TravelRepository {
        return TravelRepositoryImpl(
            travelApi = travelApi,
            context = context,
            travelDao = travelDao
        )
    }

    @Provides
    @Singleton
    fun provideStationRepository(
        travelApi: TravelApi,
        stationDao: StationDao
    ): StationRepository {
        return StationRepositoryImpl(
            travelApi = travelApi,
            stationDao = stationDao
        )
    }

    @Provides
    @Singleton
    fun provideTravelDatabase(
        @ApplicationContext context: Context,
    ): TravelDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            TravelDatabase::class.java,
            "TravelDatabase"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideTravelDao(database: TravelDatabase) = database.travelDao

    @Provides
    @Singleton
    fun provideStationDao(database: TravelDatabase) = database.stationDao

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .callTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)

        return okHttpClient.build()
    }


}