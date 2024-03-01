package com.example.patient.domain.repo.patients

import com.example.patient.domain.model.patiens.PatientRemoteModel

interface PatientRepository {
    suspend fun getPatients():List<PatientRemoteModel>
}