package com.example.patient.data.repository

import com.example.patient.data.datasource.PatientDataSource
import com.example.patient.domain.model.patiens.PatientRemoteModel
import javax.inject.Inject

class PatientRepositoryImpl @Inject constructor(private val patientDataSource: PatientDataSource) {

    suspend fun getPatients(): List<PatientRemoteModel> {
        return patientDataSource.getPatients().data.sortedBy { it.name }
    }
}