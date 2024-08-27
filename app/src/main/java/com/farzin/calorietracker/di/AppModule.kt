package com.farzin.calorietracker.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.farzin.core.data.preferences.DefaultPreferences
import com.farzin.core.domain.preferences.Preferences
import com.farzin.core.domain.use_case.FilterOutDigitsUseCase
import com.farzin.onboarding_domain.use_case.ValidateNutrientsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext app: Context
    ) : SharedPreferences{
        return app.getSharedPreferences("shared_pref",MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providePreferences(sharedPreferences: SharedPreferences) : Preferences{
        return DefaultPreferences(sharedPreferences)
    }


    @Provides
    @Singleton
    fun provideFilterOutDigitsUseCase() = FilterOutDigitsUseCase()

}