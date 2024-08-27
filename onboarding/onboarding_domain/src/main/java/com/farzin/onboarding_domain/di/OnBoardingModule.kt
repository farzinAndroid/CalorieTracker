package com.farzin.onboarding_domain.di

import com.farzin.onboarding_domain.use_case.ValidateNutrientsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
object OnBoardingModule {

    @Provides
    @ViewModelScoped
    fun provideValidateNutrientUseCase() = ValidateNutrientsUseCase()

}