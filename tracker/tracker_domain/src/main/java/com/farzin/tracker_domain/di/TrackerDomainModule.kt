package com.farzin.tracker_domain.di

import com.farzin.core.domain.preferences.Preferences
import com.farzin.tracker_domain.repository.TrackerRepository
import com.farzin.tracker_domain.use_case.CalculateMealNutrientsUseCase
import com.farzin.tracker_domain.use_case.DeleteTrackFoodUseCase
import com.farzin.tracker_domain.use_case.GetFoodForDateUseCase
import com.farzin.tracker_domain.use_case.InsertTrackFoodUseCase
import com.farzin.tracker_domain.use_case.SearchFoodUseCase
import com.farzin.tracker_domain.use_case.TrackerUseCasesWrapperClass
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {


    @Provides
    @ViewModelScoped
    fun provideTrackerUseCases(
        repository: TrackerRepository,
        preferences: Preferences
    ) : TrackerUseCasesWrapperClass {
        return TrackerUseCasesWrapperClass(
            getFoodForDateUseCase = GetFoodForDateUseCase(repository),
            searchFoodUseCase = SearchFoodUseCase(repository),
            deleteTrackFoodUseCase = DeleteTrackFoodUseCase(repository),
            insertTrackFoodUseCase = InsertTrackFoodUseCase(repository),
            calculateMealNutrientsUseCase = CalculateMealNutrientsUseCase(preferences)
        )
    }

}