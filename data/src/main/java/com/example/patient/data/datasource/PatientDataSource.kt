package com.example.patient.data.datasource

import com.example.patient.domain.model.add.AddPatientsRemoteModel
import com.example.patient.domain.model.add.BodyAddPatientModel
import com.example.patient.domain.model.patiens.PatientWrappedRemoteModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PatientDataSource {

    @GET("patients")
    suspend fun getPatients(): PatientWrappedRemoteModel

    @POST("patients")
    suspend fun addPatients(@Body bodyAddPatientModel: BodyAddPatientModel): AddPatientsRemoteModel
}