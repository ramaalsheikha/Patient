package com.example.patient.data.di

import com.example.patient.data.datasource.PatientDataSource
import com.example.patient.data.repository.PatientRepositoryImpl
import com.example.patient.domain.repo.patients.PatientRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun provideRepo(patientDataSource: PatientDataSource): PatientRepository {

        return PatientRepositoryImpl(patientDataSource)
    }
}