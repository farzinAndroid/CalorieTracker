package com.farzin.tracker_domain.use_case

import com.farzin.tracker_domain.model.TrackedFood
import com.farzin.tracker_domain.repository.TrackerRepository

class DeleteTrackFoodUseCase(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(trackedFood: TrackedFood){
        repository.deleteTrackedFood(trackedFood)
    }

}