package com.example.patient.domain.usecase.add

import com.example.patient.domain.model.add.AddPatientsRemoteModel
import com.example.patient.domain.model.add.BodyAddPatientModel
import com.example.patient.domain.repo.patients.PatientRepository
import javax.inject.Inject

class AddPatientUseCase @Inject constructor(private val repository: PatientRepository) {

    suspend operator fun invoke(bodyAddPatientModel: BodyAddPatientModel): AddPatientsRemoteModel {
       return repository.addPatients(bodyAddPatientModel)
    }
}