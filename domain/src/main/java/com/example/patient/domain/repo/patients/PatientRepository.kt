package com.example.patient.domain.repo.patients

import com.example.patient.domain.model.add.AddPatientsRemoteModel
import com.example.patient.domain.model.add.BodyAddPatientModel
import com.example.patient.domain.model.delete.PatientDeleteResponseModel
import com.example.patient.domain.model.patiens.PatientRemoteModel

interface PatientRepository {
    suspend fun getPatients():List<PatientRemoteModel>
    suspend fun addPatients(bodyAddPatientModel: BodyAddPatientModel): AddPatientsRemoteModel
    suspend fun deletePatient(id:String):PatientDeleteResponseModel
}