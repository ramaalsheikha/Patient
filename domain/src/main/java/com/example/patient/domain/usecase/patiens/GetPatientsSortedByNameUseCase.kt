package com.example.patient.domain.usecase.patiens

import com.example.patient.domain.model.patiens.PatientRemoteModel
import com.example.patient.domain.repo.PatientRepository
import javax.inject.Inject

class GetPatientsSortedByNameUseCase@Inject constructor(private val repository: PatientRepository){

    suspend operator fun invoke():List<PatientRemoteModel>{
        return repository.getPatients()
    }
}