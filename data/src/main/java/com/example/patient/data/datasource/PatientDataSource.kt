package com.example.patient.data.datasource

import com.example.patient.domain.model.add.AddPatientsRemoteModel
import com.example.patient.domain.model.add.BodyAddPatientModel
import com.example.patient.domain.model.base.BaseWrapped
import com.example.patient.domain.model.delete.PatientDeleteResponseModel
import com.example.patient.domain.model.patiens.PatientRemoteModel
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PatientDataSource {

    @GET("patients")
    suspend fun getPatients(): BaseWrapped<PatientRemoteModel>

    @POST("patients")
    suspend fun addPatients(@Body bodyAddPatientModel: BodyAddPatientModel): AddPatientsRemoteModel

    @DELETE("patients/{id}")
    suspend fun deletePatients(@Path("id") id: String): PatientDeleteResponseModel

    @GET("patients/{id}")
    suspend fun getPatientById(@Path("id") id:String):BaseWrapped<PatientRemoteModel>
}