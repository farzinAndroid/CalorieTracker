package com.farzin.tracker_domain.use_case

import com.farzin.tracker_domain.model.TrackedFood
import com.farzin.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class GetFoodForDateUseCase(
    private val repository: TrackerRepository
) {

    operator fun invoke(
        date:LocalDate
    ) : Flow<List<TrackedFood>>{
        return repository.getFoodForDate(date)
    }

}