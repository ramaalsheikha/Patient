package com.example.patient.domain.usecase.delete

import com.example.patient.domain.model.add.AddPatientsRemoteModel
import com.example.patient.domain.model.add.BodyAddPatientModel
import com.example.patient.domain.model.delete.PatientDeleteResponseModel
import com.example.patient.domain.repo.patients.PatientRepository
import javax.inject.Inject

class DeletePatientUseCase @Inject constructor(private val repository: PatientRepository) {

    suspend operator fun invoke(id:String): PatientDeleteResponseModel {
       return repository.deletePatient(id)
    }
}