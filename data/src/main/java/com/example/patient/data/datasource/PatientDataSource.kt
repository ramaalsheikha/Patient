package com.example.patient.data.datasource

import com.example.patient.domain.model.patiens.PatientWrappedRemoteModel
import retrofit2.http.GET

interface PatientDataSource {

    @GET("patients")
    suspend fun getPatients(): PatientWrappedRemoteModel
}