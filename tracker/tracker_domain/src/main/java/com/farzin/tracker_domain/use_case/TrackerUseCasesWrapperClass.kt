package com.farzin.tracker_domain.use_case

data class TrackerUseCasesWrapperClass(
    val insertTrackFoodUseCase: InsertTrackFoodUseCase,
    val searchFoodUseCase: SearchFoodUseCase,
    val getFoodForDateUseCase: GetFoodForDateUseCase,
    val deleteTrackFoodUseCase: DeleteTrackFoodUseCase,
    val calculateMealNutrientsUseCase: CalculateMealNutrientsUseCase,
)