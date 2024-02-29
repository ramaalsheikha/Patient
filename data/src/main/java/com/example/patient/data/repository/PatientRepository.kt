package com.example.patient.data.repository

import com.example.patient.data.datasource.PatientDataSource
import com.example.patient.data.domain.model.patiens.PatientRemoteModel
import javax.inject.Inject

class PatientRepository @Inject constructor(private val patientDataSource: PatientDataSource) {

    suspend fun getPatients(): List<PatientRemoteModel> {
        return patientDataSource.getPatients().data.sortedBy { it.name }
    }
}