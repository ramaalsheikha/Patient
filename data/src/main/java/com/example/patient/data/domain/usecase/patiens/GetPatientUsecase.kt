package com.example.patient.data.domain.usecase.patiens

import com.example.patient.data.domain.model.patiens.PatientRemoteModel
import com.example.patient.data.repository.PatientRepository
import javax.inject.Inject

class GetPatientUseCase @Inject constructor(private val repository: PatientRepository){

    suspend fun invoke():List<PatientRemoteModel>{
        return repository.getPatients()
    }
}