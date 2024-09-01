package com.farzin.tracker_data.di

import android.content.Context
import androidx.room.Room
import com.farzin.tracker_data.local.TrackerDatabase
import com.farzin.tracker_data.remote.OpenFoodApi
import com.farzin.tracker_data.remote.OpenFoodApi.Companion.BASE_URL
import com.farzin.tracker_data.repository.TrackerRepositoryImpl
import com.farzin.tracker_domain.repository.TrackerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object TrackerDataModule {

    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .build()


    @Provides
    @Singleton
    fun provideOpenFoodApi(okHttpClient: OkHttpClient): OpenFoodApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create()

    @Provides
    @Singleton
    fun provideTrackerDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            TrackerDatabase::class.java,
            "tracker_db"
        ).build()


    @Provides
    @Singleton
    fun provideTrackerRepository(
        openFoodApi: OpenFoodApi,
        database: TrackerDatabase,
    ): TrackerRepository {
        return TrackerRepositoryImpl(
            trackerDao = database.dao,
            api = openFoodApi
        )

    }


}