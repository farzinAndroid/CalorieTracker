package com.farzin.tracker_data.repository

import com.farzin.tracker_data.local.TrackerDao
import com.farzin.tracker_data.mapper.toTrackableFood
import com.farzin.tracker_data.mapper.toTrackedFood
import com.farzin.tracker_data.mapper.toTrackedFoodEntity
import com.farzin.tracker_data.remote.OpenFoodApi
import com.farzin.tracker_domain.model.TrackableFood
import com.farzin.tracker_domain.model.TrackedFood
import com.farzin.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class TrackerRepositoryImpl(
    private val trackerDao: TrackerDao,
    private val api: OpenFoodApi,
) : TrackerRepository {
    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int,
    ): Result<List<TrackableFood>> {
        return try {
            val searchDto = api.searchFood(
                query = query,
                page = page,
                pageSize = pageSize
            )
            Result.success(
                searchDto.products
                    .filter {
                        val calculatedCalories =
                            it.nutriments.carbohydrates_100g * 4f +
                                    it.nutriments.proteins_100g * 4f +
                                    it.nutriments.fat_100g * 9f
                        val lowerBound = calculatedCalories * 0.99f
                        val upperBound = calculatedCalories * 1.01f
                        it.nutriments.energyKcal100g in (lowerBound..upperBound)

                    }
                    .mapNotNull {
                        it.toTrackableFood()
                    }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun insertTrackedFood(trackedFood: TrackedFood) {
        trackerDao.insertTrackedFood(trackedFood.toTrackedFoodEntity())
    }

    override suspend fun deleteTrackedFood(trackedFood: TrackedFood) {
        trackerDao.deleteTrackedFood(trackedFood.toTrackedFoodEntity())
    }

    override fun getFoodForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        return trackerDao.getFoodsForDate(
            localDate.dayOfMonth,
            localDate.monthValue,
            localDate.year
        ).map { entity ->
            entity.map {
                it.toTrackedFood()
            }
        }
    }
}