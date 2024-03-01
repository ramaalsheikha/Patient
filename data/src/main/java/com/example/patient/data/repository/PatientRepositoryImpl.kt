package com.example.patient.data.repository

import com.example.patient.data.datasource.PatientDataSource
import com.example.patient.domain.model.AddPatientsRemoteModel
import com.example.patient.domain.model.add.BodyAddPatientModel
import com.example.patient.domain.model.patiens.PatientRemoteModel
import com.example.patient.domain.repo.patients.PatientRepository
import javax.inject.Inject

class PatientRepositoryImpl  @Inject constructor(private val patientDataSource: PatientDataSource) :
    PatientRepository {

   override suspend fun getPatients(): List<PatientRemoteModel> {
        return patientDataSource.getPatients().data.sortedBy { it.name }
    }

    override suspend fun addPatients(bodyAddPatientModel: BodyAddPatientModel): AddPatientsRemoteModel {
        return patientDataSource.addPatients(bodyAddPatientModel)
    }
}